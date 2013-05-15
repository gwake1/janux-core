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

package biz.janux.commerce.payment.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;



import biz.janux.commerce.payment.interfaces.transaction.Authorization;
 /**
  * 
  * @author Nilesh
  * </br>
  * Class for Authorization model
  * </br>
  * Implements Authorization and Serializable
  *
  */
public class Authorizationmodel implements Authorization , Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 9059133306889379204L;

	private long MerchantId;
	
	private long instrumentId;
	
	private BigDecimal authAmount;
	
	private String authType;
	 	
	/*
 	 * (non-Javadoc)
 	 * @see biz.janux.commerce.payment.interfaces.transaction.Transaction#getMerchantId()
 	 */
	public long getMerchantId() {
		return MerchantId;
	}
	/*
	 * (non-Javadoc)
	 * @see biz.janux.commerce.payment.interfaces.transaction.Transaction#setMerchantId(long)
	 */
	public void setMerchantId(long merchantId) {
		MerchantId = merchantId;
	}
	/*
	 * (non-Javadoc)
	 * @see biz.janux.commerce.payment.interfaces.transaction.Transaction#getInstrumentId()
	 */
	public long getInstrumentId() {
		return instrumentId;
	}
	/*
	 * (non-Javadoc)
	 * @see biz.janux.commerce.payment.interfaces.transaction.Transaction#setInstrumentId(long)
	 */
	public void setInstrumentId(long instrumentId) {
		this.instrumentId = instrumentId;
	}
	/*
	 * (non-Javadoc)
	 * @see biz.janux.commerce.payment.interfaces.transaction.Authorization#getAuthAmount()
	 */
	public BigDecimal getAuthAmount() {
		return authAmount;
	}
	/*
	 * (non-Javadoc)
	 * @see biz.janux.commerce.payment.interfaces.transaction.Authorization#setAuthAmount(java.math.BigDecimal)
	 */
	public void setAuthAmount(BigDecimal authAmount) {
		this.authAmount = authAmount;
	}
	
  
}
