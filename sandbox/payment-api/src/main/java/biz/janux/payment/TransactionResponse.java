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

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * Represents the response of a request for a {@link Transaction}
 * 
 * @author Nilesh
 * @author albertobuffagni@gmail.com
 * 
 **/
public interface TransactionResponse  extends Serializable {
	
	/**
	 * Date of the transaction response.
	 */
	public Date getDate();
	public void setDate(Date date);
	
	/**
	 * The bytes received from the payment processor. 
	 */
	public byte[] getOriginalBytes();
	public void setOriginalBytes(byte[] originalBytes);
	
	/**
	 * It is not persist.
	 */
	public String getErrorDescription();
	public void setErrorDescription(String errorDescription);
	
	/**
	 * This value depends of the response code
	 */
	public boolean isApproved();
	public void setApproved(boolean approved);
	
}
