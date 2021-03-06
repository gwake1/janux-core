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

import biz.janux.payment.MerchantAccount;


/**
 * Represents a Credit Card Transaction 
 * 
 * All credit card charges take place in two stages: authorization and capture.
 * 
 * Authorization is the act of obtaining the authorization for a certain dollar amount 
 * on a customer's credit card (i.e., making sure that the card has enough funds to cover the amount). 
 * No actual money changes hands during the authorization, but a hold is placed on those authorized funds.
 * 
 * Capture is the act of actually transferring the previously authorized funds from the card holder bank account 
 * to the merchant bank account. 
 * 
 * @author Nilesh
 * @author albertobuffagni@gmail.com
 *
 */
public interface Transaction <T extends TransactionResponse> extends Serializable{
	
	/**       
	 * A random alphanumeric string that uniquely identifies this Credit Card in the Janux Payment
	 * Service. This is the code that external clients must use to reference the Credit Card when
	 * calling the Janux Payment Service.
	 */
	public String getUuid();
	
	/**
	 * Date of the transaction.
	 */
	public Date getDate();
	public void setDate(Date date);
	
	public BusinessUnit getBusinessUnit();
	public void setBusinessUnit(BusinessUnit businessUnit);
	
	public T getTransactionResponse();
	public void setTransactionResponse(T transactionResponse);
	
	/**
	 * If false when a user decides avoid a transaction.   
	 */
	public boolean isEnabled();
	public void setEnabled(boolean enabled);
	
}
