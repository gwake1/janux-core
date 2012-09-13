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

package biz.janux.commerce.payment.interfaces.service;

import biz.janux.commerce.payment.implementation.vendor.vital.transaction.FormatDetails.DetailType;
import biz.janux.commerce.payment.interfaces.response.AddressVerificationResponse;
import biz.janux.commerce.payment.interfaces.response.AuthorizationResponse;
import biz.janux.commerce.payment.interfaces.response.SettlementResponse;
import biz.janux.commerce.payment.interfaces.transaction.AddressVerification;
import biz.janux.commerce.payment.interfaces.transaction.Authorization;
import biz.janux.commerce.payment.interfaces.transaction.Settlement;

/**
 * 
 * @author Nilesh
 * </br>
 * Interface for TransactionService
 *
 */
public interface TransactionService {
	/**
	 * 
	 * @param transaction
	 * @param type
	 * @return Settlement Response
	 */
	public SettlementResponse process(Settlement transaction , DetailType type);
	/**
	 * 
	 * @param transaction
	 * @return AddressVerification Response
	 */
	public AddressVerificationResponse process(AddressVerification transaction);
	/**
	 * 
	 * @param transaction
	 * @return Authorization Response
	 */
	public AuthorizationResponse process(Authorization transaction);
	
}
