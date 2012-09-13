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

package biz.janux.commerce.payment.implementation.vendor.vital.transaction;

import java.math.BigDecimal;

import biz.janux.commerce.payment.implementation.vendor.vital.helper.VitalHelper;
import biz.janux.commerce.payment.interfaces.response.AuthorizationResponse;
import biz.janux.commerce.payment.interfaces.transaction.Authorization;
import biz.janux.commerce.payment.model.VitalMerchantAccount;
import biz.janux.commerce.payment.util.Constants;

public class Incremental extends VitalAuthorization{
	// Section 4.75
	// Transaction Identifier
	// 15 character field
	// ONLY USED IN INCREMENTAL/REVERSAL TRANSACTIONS
	String transactionIdentifier = null;
	
	/**
	 * Prepares an incremental authorization request.
	 * @param merchant
	 * @param response
	 */
	public Incremental(
		Authorization merchant,
		AuthorizationResponse response,
		BigDecimal additionalAmount)
	{
		super(merchant);
		
		this.requestedACI = Constants.REQUESTED_ACI_INCREMENTAL_AUTHORIZATION_REQUEST;
		setTransactionIdentifier(response.getTransactionIdentifier());
		setTransactionAmount(additionalAmount);
	}
   	public void setTransactionIdentifier(String transactionIdentifier)
   	{
		this.transactionIdentifier = transactionIdentifier;
   	}
   	public String getTransactionIdentifier()
   	{
		// NOTE, THIS SHOULD ALWAYS BE 15 CHARACTERS I THINK
	   	// MAY ONLY BE AVAILABLE FOR VISA/MASTERCARD
	   	return VitalHelper.leftJustifyZero(transactionIdentifier, 15);
   	}
	public String getTransactionID()
	{
		// TRANSACTION IDENTIFIER FOR INCREMENTAL (and REVERSAL)
		// HOWEVER, IT SEEMS THAT NON MC/VISA ARE BEING APPENDED AS 00000000000000
		// IN THE DATABASE INSTEAD OF NULL..	
		
		return getTransactionIdentifier();
	}
}
