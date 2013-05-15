package biz.janux.payment.gateway;

import biz.janux.payment.Credential;
import biz.janux.payment.CredentialHolder;

import java.util.HashMap;
import java.util.Map;

import org.jasypt.encryption.pbe.PBEStringEncryptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ***************************************************************************************************
 * This class implements a default initialization strategy that can be used to decrypt the password
 * field of a Set of {@link Credential} objects, for example those contained in a Service that
 * implements the CredentialHolder interface and are used to access a protected back-end that performs
 * encrypted storage; this class should be sufficient in most cases, and could be easily extended or
 * replaced, should the initialization requirements of a specific Service be more complex.
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @see AppInitializer
 * @since 0.4.0 - 2012-07-22
 ***************************************************************************************************
 */
public class AppInitStrategyDefault implements AppInitStrategy, CredentialHolder<Credential>
{
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected String passwordEncryptionKey;
	protected PBEStringEncryptor stringEncryptor;
	protected Map<String, Credential> credentials;

	public String getPasswordEncryptionKey() { return passwordEncryptionKey; }
	public void setPasswordEncryptionKey(String o) { passwordEncryptionKey = o;}

	/** 
	 * Contains the password strings to decrypt
	 */
	public Map<String, Credential> getCredentials() 
	{ 
		if (this.credentials == null) { this.credentials = new HashMap<String, Credential>(); }
		return this.credentials;
	}

	public void setCredentials(Map<String, Credential> m) { this.credentials = m; }

	/**
	 * The encryptor that is used to encrypt/decrypt the various system-level passwords on which the
	 * application relies
	 */
	public PBEStringEncryptor getStringEncryptor()
	{
		if (this.stringEncryptor == null) {
			throw new IllegalStateException("PasswordDecryptor.stringEncryptor is null and needs to be properly configured");
		}

		return this.stringEncryptor;
	}

	public void setStringEncryptor(PBEStringEncryptor o) {
		this.stringEncryptor = o;
	}


	/** decrypts the password of the Encrypted Storage Service */
	public void preInit()
	{
		// Remember to do this in your strategy, or you won't be able to decrypt
		log.warn("Initializing StringDecryptor with Password Encryption Key...");
		this.getStringEncryptor().setPassword(this.getPasswordEncryptionKey());

		for ( Credential credential : this.getCredentials().values() ) 
		{
			String encryptedPass = credential.getPassword();
			log.debug("encrypted password for user '{}' is: '{}'", credential.getUsername(), encryptedPass);

			String decryptedPass = this.getStringEncryptor().decrypt(encryptedPass);

			// NEVER LOG THE PASSWORD IN A STRATEGY DEPLOYED IN PRODUCTION
			log.trace("decrypted password for user '{}' is: '{}'", credential.getUsername(), decryptedPass);

			log.debug("Setting decrypted password in credential");
			credential.setPassword(decryptedPass);
		}
	}

	public void postInit() {
		log.debug("Nothing to do in postInit()");
	}

} // end class AppInitStrategyDefault
