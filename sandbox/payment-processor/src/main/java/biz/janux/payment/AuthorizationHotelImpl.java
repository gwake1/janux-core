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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.janux.util.JanuxToStringStyle;

public class AuthorizationHotelImpl extends AuthorizationImpl implements AuthorizationHotel{

		Integer stayDuration;

		/**
		 * used by hibernate 
		 */
		public AuthorizationHotelImpl() {
			super();
		}

		public AuthorizationHotelImpl(BusinessUnit businessUnit, CreditCard creditCard, BigDecimal authAmount,Integer stayDuration, String externalSourceId) {
			super(businessUnit,creditCard,authAmount, externalSourceId);
			setStayDuration(stayDuration);
		}

		public Integer getStayDuration() {
			return stayDuration;
		}

		public void setStayDuration(Integer stayDuration) {
			this.stayDuration = stayDuration;
		}
		
	
		public String toString() 
		{
			ToStringBuilder sb = new ToStringBuilder(this, JanuxToStringStyle.COMPACT);
			
			sb.append(super.toString());
			if (getStayDuration() != null)	sb.append("stayDuration",   getStayDuration());
			return sb.toString();
		}


		public boolean equals(Object other)
		{
			if ( (this == other) ) return true;
			if ( !(other instanceof AuthorizationHotelImpl) ) return false;

			AuthorizationHotelImpl castOther = (AuthorizationHotelImpl)other; 

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
