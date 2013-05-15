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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.janux.bus.persistence.PersistentVersionableAbstract;
import org.janux.util.JanuxToStringStyle;

public class BatchNumberImpl extends PersistentVersionableAbstract implements BatchNumber{

	int number;
	MerchantAccount merchantAccount;

	public BatchNumberImpl() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public MerchantAccount getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(MerchantAccount merchantAccount) {
		this.merchantAccount = merchantAccount;
	}
	
	public String toString() 
	{
		ToStringBuilder sb = new ToStringBuilder(this, JanuxToStringStyle.COMPACT);
		
		sb.append(super.toString());
		sb.append("number", getNumber());
		if (getMerchantAccount() != null)
			sb.append("merchantAccount", getMerchantAccount());

		return sb.toString();
	}

	public boolean equals(Object other)
	{
		if ( (this == other) ) return true;
		if ( !(other instanceof BatchNumberImpl) ) return false;

		BatchNumberImpl castOther = (BatchNumberImpl)other; 

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
