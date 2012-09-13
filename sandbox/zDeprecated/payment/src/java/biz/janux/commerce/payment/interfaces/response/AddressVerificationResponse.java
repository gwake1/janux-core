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

import biz.janux.commerce.payment.interfaces.response.codes.AddressVerificationResponseCodes;
/**
 * 
 * @author Nilesh
 * </br>
 * Interface for AddressVerificationResponse
 * </br>
 * It extends TransactionResponse and AddressVerificationResponseCodes
 *
 */
public interface AddressVerificationResponse extends TransactionResponse, AddressVerificationResponseCodes{
	
	 /**
     * @return boolean
     * is ExactMatch
     * */
	public boolean isExactMatch();
	
	 /**
     * @return boolean
     * is AddressMatch
     * */
    public boolean isAddressMatch();
    
    /**
     * @return boolean
     * is Zip Match
     * */
    public boolean isZipMatch();
    
    /**
     * @return boolean
     * is No Match
     * */
    public boolean isNoMatch();
    
    
    /**
     * @return boolean
     * is unavailable
     * */
    public boolean isUnavailable();
    
    /**
     * 
     * @return string 
     */
    public String addressVerificationResponse();
    /**
     * 
     * @return String
     */
    public String getApprovalCode();
    /**
     * 
     * @return char
     */
    public char getAvsResultCode();

}
