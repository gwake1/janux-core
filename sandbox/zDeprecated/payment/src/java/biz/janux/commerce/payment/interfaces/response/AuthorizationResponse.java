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

import java.math.BigDecimal;

import biz.janux.commerce.payment.interfaces.response.codes.AuthorizationResponseCodes;
/**
 * 
 * @author Nilesh
 * </br>
 * Interface for AuthorizationResponse
 * </br>
 * It extends TransactionResponse and AuthorizationResponseCodes
 */ 
public interface AuthorizationResponse extends TransactionResponse, AuthorizationResponseCodes{

	/**
     * @return boolean
     * is Approved
     * */
	public boolean isApproved() ;
	/**
     * @return boolean
     * is Declined
     * */
	public boolean isDeclined() ;
	/**
     * 
     * @return string 
     */
	public String getApprovalCode();
	/**
     * 
     * @return string 
     */
	public String getAuthResponseText();
	/**
     * @return boolean
     * is Offline
     * */
	public boolean isOffline() ;
	/**
     * @return boolean
     * is Reversal
     * */
	public boolean isReversal();
	/**
     * 
     * @return long 
     */
	public long getFolioId();
	/**
	 *  @param long i
	 */
	public void setFolioId(long i);
	/**
     * @return BigDecimal
     * AuthorizationAmoun
     * */
	public BigDecimal getAuthorizationAmount();
	/**
	 *  @param BigDecimal 
	 */
	public void setAuthorizationAmount(BigDecimal authorizationAmount);
	/**
	 * 
	 * @param AuthorizationId
	 */
	public void setId(long id);
	/**
	 * 
	 * @return long
	 */
	public long getId();
	
	
}
