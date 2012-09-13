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
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.janux.util.JanuxToStringStyle;

public class SettlementItemHotelImpl  extends SettlementItemImpl implements SettlementItemHotel {
	
	BigDecimal averageRate;
	Date checkOutDate;
	
	/**
	 * used by hibernate 
	 * @deprecated
	 */
	public SettlementItemHotelImpl() {
		super();
	}
	
	public SettlementItemHotelImpl(BigDecimal amount, SettlementItemType type, Date date, CreditCard creditCard, BusinessUnit businessUnit, String purchaseIdentifier,BigDecimal averageRate, Date checkOutDate, String externalSourceId) {
		super(amount, type, date, creditCard, businessUnit, purchaseIdentifier, externalSourceId);
		this.averageRate = averageRate;
		this.checkOutDate = checkOutDate;
	}

	public BigDecimal getAverageRate() {
		return averageRate;
	}
	public void setAverageRate(BigDecimal averageRate) {
		this.averageRate = averageRate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	
	public String toString() 
	{
		ToStringBuilder sb = new ToStringBuilder(this, JanuxToStringStyle.COMPACT);
		
		sb.append(super.toString());
		if (getAverageRate() != null)    sb.append("averageRate", getAverageRate());
		if (getCheckOutDate() != null)    sb.append("checkOutDate", getCheckOutDate());
		
		return sb.toString();
	}


	public boolean equals(Object other)
	{
		if ( (this == other) ) return true;
		if ( !(other instanceof SettlementItemHotelImpl) ) return false;

		SettlementItemHotelImpl castOther = (SettlementItemHotelImpl)other; 

		return new org.apache.commons.lang.builder.EqualsBuilder()
			.append(this.getUuid(), castOther.getUuid())
			.isEquals();
	}

	public int hashCode() 
	{
		return new org.apache.commons.lang.builder.HashCodeBuilder()
			.append(this.getUuid())
			.toHashCode();
	}
	
}
