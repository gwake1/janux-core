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

package biz.janux.payment;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EncryptedStorageServiceStrongKeyImpl implements EncryptedStorageService<String, String>, CredentialHolder<Credential>
{
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Map<String, Credential> credentials;
	
	private String uriWebService;

	/** The key by which the Encryptor Credential can be found in the credential map: "ENCRYPTOR" */
	public static final String KEY_ENCRYPTOR = "ENCRYPTOR";

	/** The key by which the Decryptor Credential can be found in the Credentials map: "DECRYPTOR" */
	public static final String KEY_DECRYPTOR = "DECRYPTOR";

	/**
	 * The unique identifier of the encryption domain on the SKLES
	 */
	private long domain;

	private String usernameEncryptor;
	
	private String passwordEncryptor;
	
	private String usernameDecryptor;
	
	private String passwordDecryptor;
	
	private WebServiceClient webServiceClient;
	
	Namespace ns = Namespace.getNamespace("ns", "http://web.strongkeylite.strongauth.com/");
	XMLOutputter out = new XMLOutputter();
	
	public String encryption(String sensitiveData) {
		log.debug("Attemping to encrypt and store a credit card number in the appliance");
		
		Document requestXml = new Document();
		
		Element root = new Element("encrypt",ns);
		root.addNamespaceDeclaration(ns);

		requestXml.setRootElement(root);

		Element element;
		element = new Element("did").setText(""+getDomain());
		root.addContent(element);
		
		element = new Element("username").setText(getUsernameEncryptor());
		root.addContent(element);
		
		element = new Element("password").setText(getPasswordEncryptor());
		root.addContent(element);
		
		element = new Element("plaintext").setText(sensitiveData);
		root.addContent(element);
		
		String requestXmlToString = out.outputString(requestXml); 
		JDOMResult response= getWebServiceClient().customSendAndReceive(getUriWebService(),requestXmlToString);
		
		Document responseXml = response.getDocument();
		log.debug("Successfully encrypted and stored a credit card number in the appliance");
		return responseXml.getRootElement().getChild("return").getText();
	}

	public String decryption(String token) {
		log.debug("Attemping to deencrypt a token from the appliance");
		
		Document requestXml = new Document();
		
		Element root = new Element("decrypt",ns);
		root.addNamespaceDeclaration(ns);

		requestXml.setRootElement(root);

		Element element;
		element = new Element("did").setText(""+getDomain());
		root.addContent(element);
		
		element = new Element("username").setText(getUsernameDecryptor());
		root.addContent(element);
		
		element = new Element("password").setText(getPasswordDecryptor());
		root.addContent(element);
		
		element = new Element("token").setText(token);
		root.addContent(element);
		
		String requestXmlToString = out.outputString(requestXml); 
		JDOMResult response= getWebServiceClient().customSendAndReceive(getUriWebService(),requestXmlToString);
		
		Document responseXml = response.getDocument();
		log.debug("Successfully decrypted a token from the appliance");
		
		return responseXml.getRootElement().getChild("return").getText();
	}

	public boolean delete(String token) {
		log.debug("Attemping to delete a token from the appliance");
		
		Document requestXml = new Document();
		
		Element root = new Element("delete",ns);
		root.addNamespaceDeclaration(ns);

		requestXml.setRootElement(root);

		Element element;
		element = new Element("did").setText(""+getDomain());
		root.addContent(element);
		
		element = new Element("username").setText(getUsernameEncryptor());
		root.addContent(element);
		
		element = new Element("password").setText(getPasswordEncryptor());
		root.addContent(element);
		
		element = new Element("token").setText(token);
		root.addContent(element);
		
		String requestXmlToString = out.outputString(requestXml); 
		JDOMResult response= getWebServiceClient().customSendAndReceive(getUriWebService(),requestXmlToString);
		
		Document responseXml = response.getDocument();
		log.debug("Successfully deleted a token from the appliance");
		
		String something = responseXml.getRootElement().getChild("return").getText();
		return true;
	}

	public String search(String sensitiveData) {
		log.debug("Attemping to search a credit card number in the appliance");
		
		Document requestXml = new Document();
		
		Element root = new Element("search",ns);
		root.addNamespaceDeclaration(ns);

		requestXml.setRootElement(root);

		Element element;
		element = new Element("did").setText(""+getDomain());
		root.addContent(element);
		
		element = new Element("username").setText(getUsernameDecryptor());
		root.addContent(element);
		
		element = new Element("password").setText(getPasswordDecryptor());
		root.addContent(element);
		
		element = new Element("plaintext").setText(sensitiveData);
		root.addContent(element);
		
		String requestXmlToString = out.outputString(requestXml); 
		JDOMResult response= getWebServiceClient().customSendAndReceive(getUriWebService(),requestXmlToString);
		
		Document responseXml = response.getDocument();
		log.debug("Successfully searched a credit card number in the appliance");
		
		return responseXml.getRootElement().getChild("return").getText();
	}

	public void setWebServiceClient(WebServiceClient webServiceClient) {
		this.webServiceClient = webServiceClient;
	}

	public WebServiceClient getWebServiceClient() {
		return webServiceClient;
	}

	public void setUriWebService(String uriWebService) {
		this.uriWebService = uriWebService;
	}

	public String getUriWebService() {
		return uriWebService;
	}

	public void setDomain(long domain) {
		this.domain = domain;
	}

	public long getDomain() {
		return domain;
	}

	/*
	public void setUsernameEncryptor(String usernameEncryptor) {
		if (log.isDebugEnabled()) log.debug("setting encryptor username to: " + usernameEncryptor);
		this.usernameEncryptor = usernameEncryptor;
	}
	*/


	private String getUsernameEncryptor() {
		return this.getCredentials().get(KEY_ENCRYPTOR).getUsername();
	}

	/*
	public void setPasswordEncryptor(String passwordEncryptor) {
		if (log.isDebugEnabled()) log.debug("setting encryptor password to: " + passwordEncryptor);
		this.passwordEncryptor = passwordEncryptor;
	}
	*/

	public String getPasswordEncryptor() {
		return this.getCredentials().get(KEY_ENCRYPTOR).getPassword();
	}

	/*
	public void setUsernameDecryptor(String usernameDecryptor) {
		if (log.isDebugEnabled()) log.debug("setting decryptor username to: " + usernameDecryptor);
		this.usernameDecryptor = usernameDecryptor;
	}
	*/

	private String getUsernameDecryptor() {
		return this.getCredentials().get(KEY_DECRYPTOR).getUsername();
	}

	/*
	public void setPasswordDecryptor(String passwordDecryptor) {
		if (log.isDebugEnabled()) log.debug("setting decryptor password to: " + passwordDecryptor);
		this.passwordDecryptor = passwordDecryptor;
	}
	*/

	public String getPasswordDecryptor() {
		return this.getCredentials().get(KEY_DECRYPTOR).getPassword();
	}


	public Map<String, Credential> getCredentials() 
	{
		if (this.credentials == null) {
			this.credentials = new HashMap<String, Credential>();
		}
			return this.credentials;
	}

	public void setCredentials(Map<String, Credential> m) {
		this.credentials = m;
	}

}
