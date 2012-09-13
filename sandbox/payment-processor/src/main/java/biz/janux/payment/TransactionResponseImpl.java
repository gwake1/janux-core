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

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.janux.bus.persistence.PersistentVersionableAbstract;
import org.janux.util.JanuxToStringStyle;


public abstract class TransactionResponseImpl extends PersistentVersionableAbstract implements TransactionResponse{

	Date date;
	byte[] originalBytes;
	boolean approved;
	
	String errorDescription;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public byte[] getOriginalBytes() {
		return originalBytes;
	}
	public void setOriginalBytes(byte[] originalBytes) {
		this.originalBytes = originalBytes;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public String toString() 
	{
		ToStringBuilder sb = new ToStringBuilder(this, JanuxToStringStyle.COMPACT);

		if (getId() != null) sb.append("id",   getId());
		if (getDate() != null) sb.append("date", getDate());
		sb.append("approved", isApproved());
		if (getErrorDescription() != null)    sb.append("errorDescription", getErrorDescription());
		if (getDateCreated() != null)    sb.append("created", getDateCreated());
		if (getDateUpdated() != null)    sb.append("updated", getDateUpdated());

		return sb.toString();
	}


	public boolean equals(Object other)
	{
		if ( (this == other) ) return true;
		if ( !(other instanceof TransactionResponseImpl) ) return false;

		TransactionResponseImpl castOther = (TransactionResponseImpl)other; 

		return new org.apache.commons.lang.builder.EqualsBuilder()
			.append(this.getId(), castOther.getId())
			.isEquals();
	}

	public int hashCode() 
	{
		return new org.apache.commons.lang.builder.HashCodeBuilder()
			.append(this.getId())
			.toHashCode();
	}

}
