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

package biz.janux.commerce.payment.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * 
 * @author Nilesh
 * </br>
 * Util class for Password
 */
public class PasswordUtil{
	
	private  SecretKey m_key = null;
	private  String m_keyStr = "V4XIg0nOZGQ=";
	private  String m_keyAlgorithm = "DES";
	private  boolean m_initialized = false;
	private  String m_encryptionAlgorithm = "DES/ECB/PKCS5Padding";
	
	sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	/**
	 * @throws Exception
	 */
	private  void init() throws Exception {
		if (m_initialized)
			return; 
		SecretKeyFactory skf = SecretKeyFactory.getInstance(m_keyAlgorithm);
		DESKeySpec desKS = new DESKeySpec(decoder.decodeBuffer(m_keyStr));
		m_key = skf.generateSecret(desKS);
		m_initialized = true;
	}
	/**
	 * Method to encrypt the String
	 * @param _str
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String _str) throws Exception {
		String encryptedStr = "";
		if (_str == null || _str.trim().length() == 0) {
			return encryptedStr;
		}

		try {
			init();
			Cipher c = Cipher.getInstance(m_encryptionAlgorithm);
			c.init(Cipher.ENCRYPT_MODE, m_key);
			encryptedStr = encoder.encode(c.doFinal(_str.getBytes()));
		}
		catch (Exception e){
			System.out.println("Failed to encrypt" + e);
		}
		return encryptedStr;
	}
	/**
	 * Method to Decrypt  the String
	 * @param _str
	 * @return
	 * @throws Exception
	 */
	public synchronized  String decrypt(String _str) throws Exception {
		if (_str == null || _str.trim().length() == 0){
			return _str;
		}
		String decryptedStr = "";
		try {
			init();
			Cipher c = Cipher.getInstance(m_encryptionAlgorithm);
			c.init(Cipher.DECRYPT_MODE, m_key);
			decryptedStr = new String(c.doFinal(decoder.decodeBuffer(_str)));
		}
		catch (Exception e){
			System.out.println("Failed to decrypt" + e);
		}
		return decryptedStr;
	}
}