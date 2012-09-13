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

/**
 * 
 * Service for store and encrypt SensitiveData. 
 * It can be used by client-applications such as e-commerce, 
 * payment-processing, business-intelligence, health-care or other applications that deal with sensitive data 
 *  
 * @author albertobuffagni@gmail.com
 *
 * @param <Token> Data type of the identifier of the encrypted in the storage.
 * @param <SensitiveData> Data type of the data encrypted and stored in the encrypted storage.
 */
public interface EncryptedStorageService<Token, SensitiveData> {

	/**
	 * Encrypt and save a SensitiveData and returns a Token identifies this data.
	 */
	public Token encryption(SensitiveData sensitiveData);

	/**
	 * Decrypt the Token generated from SensitiveData
	 */
	public SensitiveData decryption(Token token);

	/**
	 * Remove a SensitiveData of the storage. If return true the sensitive data was removed.
	 */
	public boolean delete(Token token);

	/**
	 * Search if exists a Token by SensitiveData
	 */
	public Token search(SensitiveData sensitiveData);
}
