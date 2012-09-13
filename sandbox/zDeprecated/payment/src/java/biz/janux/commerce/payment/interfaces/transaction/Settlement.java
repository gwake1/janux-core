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

package biz.janux.commerce.payment.interfaces.transaction;

import java.math.BigDecimal;
import java.sql.Date;

import biz.janux.commerce.payment.model.VitalAuthorizationResponseModel;
/**
 * 
 * @author Nilesh
 * </br>
 * Interface for Settlement
 * </br>
 * Extends Transaction
 */
public interface Settlement extends Transaction {
	/**
	 * 
	 * @return boolean
	 */
	public boolean getScheduled();
	/**
	 * 
	 * @param isScheduled
	 */
	public void setScheduled(boolean isScheduled);
	/**
	 * 
	 * @param date
	 */
	public void setScheduleTime(java.sql.Date date);
	/**
	 * 
	 * @return java.sql.Date
	 */
	public Date getScheduleTime();
	/**
	 * 
	 * @param vitalAuthorization Response Model
	 */
	
	public void setVitalAuthorizationResponseModel(VitalAuthorizationResponseModel vitalAuthorizationResponseModel);
	/**
	 * 
	 * @return VitalAuthorization Response Model
	 */
	public VitalAuthorizationResponseModel getVitalAuthorizationResponseModel();
	/**
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getSettlementAmount();
	/**
	 * 
	 * @param Settlement Amount
	 */
	public void setSettlementAmount(BigDecimal settlementAmount);
	/**
	 * 
	 * @return boolean
	 */
	public boolean isApproved();
	/**
	 * 
	 * @return long
	 */
	public long getAuthId();
	/**
	 * 
	 * @param authId
	 */
	public void setAuthId(long authId);
	
}
