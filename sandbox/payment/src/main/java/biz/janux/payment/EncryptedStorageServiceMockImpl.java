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

import java.util.HashMap;
import java.util.Map;

public class EncryptedStorageServiceMockImpl implements EncryptedStorageService<String, String>, CredentialHolder<Credential>
{
	private Map<String, String> savedDataBySensitiveData = new HashMap<String, String>();
	private Map<String, String> savedDataByToken = new HashMap<String, String>();

	/** The key used to specify the user represented by one of the credentials: "USER1" */
	public static final String KEY_CREDENTIAL1 = "USER1";

	/** The key used to specify the user represented by one of the credentials: "USER2" */
	public static final String KEY_CREDENTIAL2 = "USER2";

	// TO be deprecated
	// private Credential credential;
	
	private Map credentials;
	
	public String encryption(String sensitiveData) {
		String token = "token"+sensitiveData;
		savedDataBySensitiveData.put(sensitiveData,token);
		savedDataByToken.put(token,sensitiveData);
		return token;
	}

	public String decryption(String token) {
		String sensitiveData = token.replaceAll("token", "");
		return sensitiveData;
	}

	public boolean delete(String token) {
		if (savedDataByToken.containsKey(token))
		{
			String sensitiveData = savedDataByToken.get(token); 
			savedDataByToken.remove(token);
			savedDataBySensitiveData.remove(sensitiveData);
			return true;
		}
		return false;
	}

	public String search(String sensitiveData) {
		String tokenString = savedDataBySensitiveData.get(sensitiveData);
		return tokenString;
	}

	/*
	public Credential getCredential() { return this.credential;}
	public void setCredential(Credential n) { this.credential = n; }
	*/

	/** 
	 * This bean is here to test/demonstrate decryption of system level username, 
	 * it is not otherwise used in the logic of this bean
	 */
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

} // End class EncryptedStorageServiceMockImpl
