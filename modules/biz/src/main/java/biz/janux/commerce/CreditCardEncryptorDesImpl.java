/* Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Copyright 2005-2012 janux.org */

package biz.janux.commerce;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 0.4
 */
public class CreditCardEncryptorDesImpl implements CreditCardEncryptor
{
	private static Logger log = LoggerFactory.getLogger(CreditCardEncryptorDesImpl.class);
	
	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	boolean initialized = false;
	SecretKey secretKey = null;
	String encryptionAlgorithm;
	String keyAlgorithm;
	String keyString;

	public CreditCardEncryptorDesImpl(String encryptionAlgorithm,String keyAlgorithm,String keyString) {
		super();
		
		try {
			this.encryptionAlgorithm = encryptionAlgorithm;
			this.keyAlgorithm = keyAlgorithm;
			this.keyString = keyString;
			initEncryptionProccess();
		} catch (Exception e) {
			throw new RuntimeException("Problem initializing the encryption proccess", e);
		}		
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public String decrypt(String num) throws Exception {
			if (num == null || num.trim().length() == 0){
			
				return num;
			}
		
			String decryptedStr = "";
			Cipher c = Cipher.getInstance(encryptionAlgorithm);
			c.init(Cipher.DECRYPT_MODE, secretKey);
	
			decryptedStr = new String(c.doFinal(decoder.decodeBuffer(num)));
	
			return decryptedStr;
	}

	/**
	 * {@inheritDoc} 
	 */
	public String encrypt(String num) throws Exception
	{
		String encryptedStr = "";

		if (num == null || num.trim().length() == 0) {
			return encryptedStr;
		}
		
		Cipher c = Cipher.getInstance(encryptionAlgorithm);
		c.init(Cipher.ENCRYPT_MODE, secretKey);

		encryptedStr = encoder.encode(c.doFinal(num.getBytes()));

		return encryptedStr;
	}

	/**
	 * Initialization of the encryption process
	 * @throws Exception
	 */
	synchronized void initEncryptionProccess() throws Exception {
	
		if (initialized)
			return;
	
		SecretKeyFactory skf = SecretKeyFactory.getInstance(keyAlgorithm);
		DESKeySpec desKS = new DESKeySpec(decoder.decodeBuffer(keyString));
		secretKey = skf.generateSecret(desKS);
	
		initialized = true;
	}

	public sun.misc.BASE64Decoder getDecoder() {
		return decoder;
	}

	public void setDecoder(sun.misc.BASE64Decoder decoder) {
		this.decoder = decoder;
	}

	public sun.misc.BASE64Encoder getEncoder() {
		return encoder;
	}

	public void setEncoder(sun.misc.BASE64Encoder encoder) {
		this.encoder = encoder;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKey secretKey) {
		this.secretKey = secretKey;
	}

	public String getEncryptionAlgorithm() {
		return encryptionAlgorithm;
	}

	public void setEncryptionAlgorithm(String encryptionAlgorithm) {
		encryptionAlgorithm = encryptionAlgorithm;
	}

	public String getKeyAlgorithm() {
		return keyAlgorithm;
	}

	public void setKeyAlgorithm(String key) {
		keyAlgorithm = key;
	}

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String str) {
		keyString = str;
	}


}
