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
import java.sql.Date;
import biz.janux.commerce.payment.interfaces.transaction.Settlement;
/**
 * 
 * @author Nilesh
 *	</br>
 *	Model Class for Settlement implements Settlement and Serializable
 */
public class SettlementModel implements Settlement , Serializable {
	
	private static final long serialVersionUID = 1L;

	private long merchantId;
	
	private long instrumentId;
	
	private boolean isScheduled;
	
	private boolean isApproved;
	
	public Date systemDate;
	
	private java.util.Date localTransactionDateTime;
	
	private BigDecimal settlementAmount;
	
	private long authId;
	
	VitalAuthorizationResponseModel vitalAuthorizationResponseModel;
	
	public SettlementModel() {	}
	/**
	 * 
	 * @param vitalAuthorizationResponseModel
	 * @param settlementAmount
	 */
	public SettlementModel(VitalAuthorizationResponseModel vitalAuthorizationResponseModel , BigDecimal settlementAmount) {
		this.vitalAuthorizationResponseModel = vitalAuthorizationResponseModel;
		this.merchantId = vitalAuthorizationResponseModel.getMerchantId();
		this.instrumentId = vitalAuthorizationResponseModel.getInstrumentId();
		this.isApproved = vitalAuthorizationResponseModel.isApproved();
		this.settlementAmount = settlementAmount;
		this.authId =vitalAuthorizationResponseModel.getId();
	}
	/**
	 * @return long
	 */
	public long getInstrumentId() {
		return instrumentId;
	}
	/**
	 * @param instrumentId
	 */
	public void setInstrumentId(long instrumentId) {
		this.instrumentId = instrumentId;
	}
	/**
	 * @return boolean
	 */
	public boolean getScheduled() {
		return isScheduled;
	}
	/**
	 * @param isScheduled
	 */
	public void setScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}
	/**
	 * @return java.sql.Date
	 */
	public Date getScheduleTime() {
		return systemDate;
	}
	/**
	 * @param java.sql.Date date
	 */
	public void setScheduleTime(java.sql.Date date) {
		this.systemDate = date;		
	}
	/**
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getLocalTransactionDateTime() {
		return localTransactionDateTime;
	}
	/**
	 * 
	 * @param localTransactionDateTime
	 */
	public void setLocalTransactionDateTime(
			java.util.Date localTransactionDateTime) {
		this.localTransactionDateTime = localTransactionDateTime;
		
	}
	/**
	 * @return long
	 */
	public long getMerchantId() {
		return merchantId;
	}
	/**
	 * @param merchantId
	 */
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * @return VitalAuthorizationResponseModel
	 */
	public VitalAuthorizationResponseModel getVitalAuthorizationResponseModel() {
		return vitalAuthorizationResponseModel;
	}
	/**
	 * @param vitalAuthorizationResponseModel
	 */
	public void setVitalAuthorizationResponseModel(VitalAuthorizationResponseModel vitalAuthorizationResponseModel) {
		this.vitalAuthorizationResponseModel = vitalAuthorizationResponseModel;
	}
	/**
	 * @return BigDecimal
	 */
	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}
	/**
	 * @param settlementAmount
	 */
	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	/**
	 * @return boolean
	 */
	public boolean isApproved() {
		return isApproved;
	}
	/**
	 * @return long
	 */
	public long getAuthId() {
		return authId;
	}
	/**
	 * @param authId
	 */
	public void setAuthId(long authId) {
		this.authId = authId;
	}
}
