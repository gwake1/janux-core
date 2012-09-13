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

import org.apache.log4j.Logger;

import biz.janux.commerce.payment.util.Constants;



public class CreditDetail extends FormatDetails{
	
	private static final Logger logger = Logger.getLogger(CreditDetail.class);
	public CreditDetail() {
		if (logger.isDebugEnabled()) {
			logger.debug("CreditDetail()");
		}
		setTransactionCode(Constants.TRANSACTION_CODE_CREDIT_RETURN);
		setRequestedACI(Constants.REQUESTED_ACI_CPS_CAPABLE);
		setReturnedACI(' ');
		setAuthorizationSourceCode(Constants.AUTHORIZATION_SOURCE_CODE_NOT_AUTHORIZED);
		// auto generate for credit card tran?
		// auto generated from settlement object?
		setTransactionSequenceNumber("0000");
		setResponseCode("  ");
		setApprovalCode("      ");
		setAvsResultCode('0');
		setTransactionIdentifier("000000000000000");
		setValidationCode("    ");

		setTransactionStatusCode(Constants.TRANSACTION_STATUS_CODE_NO_REVERSAL);
	}
	public String getAuthorizedAmountString() {
		return "000000000000";
	}
	public String getTotalAuthorizedAmountString() {
		return "000000000000";
	}
	public BigDecimal getSettlementAmount() {
		return settlementAmount.abs();
	}
}
