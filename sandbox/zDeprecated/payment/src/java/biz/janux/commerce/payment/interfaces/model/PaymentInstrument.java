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

package biz.janux.commerce.payment.interfaces.model;

/**
 * @author Nilesh
 * Interface for PaymentInstrument
 *
 */
public interface PaymentInstrument {
	
	/**
     * 
     * @return long
     */
	public long getCreditCardId();

	/**
     * 
     * @return String
     */
    public String getCardNumber();

    /**
     * 
     * @return String
     */
    public String getExpirationDate();

    /**
     * 
     * @return String
     */
    public String getLoggableInfo();
    
    
    /**
     * 
     * @return boolean
     */
    public boolean encryptCardNumber();
    
    /**
     * 
     * @return boolean
     */
    public boolean decryptCardNumber();
    
    /**
     * 
     * @return String
     */
    public String getUUID();
    
    /**
     * 
     * @param String
     */
    public void setUUID(String UUID);
}
