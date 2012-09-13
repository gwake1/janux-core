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

package biz.janux.commerce.payment.interfaces.response;

import java.io.Serializable;
import biz.janux.commerce.payment.interfaces.response.codes.ResponseCodes;
/**
 * @author Nilesh
 * extends Serializable, ResponseCodes
 *
 **/
public interface TransactionResponse extends Serializable, ResponseCodes{
 	/**
 	 * 
 	 * @return String
 	 */	
	public String getErrorCode();
	/**
	 * 
	 * @return String
	 */
	public String getStatus();
	/**
	 * 
	 * @return String
	 */
	public String getErrorData();
	/**
	 * 
	 * @return byte[]
	 */
	public byte[] getBytes();
	/**
	 * 
	 * @return String
	 */
	public String getDetailedResponseDefinition();
	/**
	 * 
	 * @return String
	 */
	public String getTransactionSequenceNumber();
	/**
	 * 
	 * @return String
	 */
	public String getTransactionIdentifier();
	/**
	 * 
	 * @return String
	 */
	public String getLocalTransDate() ;
	/**
	 * 
	 * @return String
	 */
	public String getLocalTransTime();
}
