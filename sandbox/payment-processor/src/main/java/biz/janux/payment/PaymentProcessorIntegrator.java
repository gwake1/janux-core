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

import java.math.BigDecimal;

/**
 * Contains the methods used for communicate with a payment processor. Such as TSYS
 * 
 * @author albertobuffagni@gmail.com
 *
 */
public interface PaymentProcessorIntegrator {

	/**
	 * Send for process the authorization to payment processor.
	 */
	public AuthorizationResponse processAuthorization(Authorization authorization, BigDecimal authAmount, AuthorizationType authorizationType);
	
	/**
	 * Process the offline authorization to payment processor.
	 */
	public AuthorizationResponse processAuthorizationOffline(Authorization authorization, BigDecimal authAmount, AuthorizationType authorizationType,String approvalCode);
	
	/**
	 * Send for process the settlement to payment processor.
	 */
	public SettlementResponse processSettlement(Settlement settlement,int batchNumber);
	

	/**
	 * Return the correct batch number.
	 * Checks of the limit of the Batch Number of the payment processor. 
	 */
	public int checkLimitBatchNumber(int batchNumber);
	
	/**
	 * Return the correct transaction sequence number.
	 * Checks of the limit of the transaction sequence number of the payment processor. 
	 */
	public int checkLimitTransactionSequenceNumber(int transactionSequenceNumber);

	
}
