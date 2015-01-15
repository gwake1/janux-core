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

package org.janux.bus.security;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class AccountSettingImpl implements AccountSetting, java.io.Serializable
{
	private static final long serialVersionUID = 20070620L;

	String tag;
	String value;
	
	public AccountSettingImpl() {}
	
	public AccountSettingImpl(String tag,String value)
	{
		this.tag = tag;
		this.value = value;
	}

	public String getTag() {
		return tag;
	}

	public String getValue() {
		return value;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean equals(Object other)
	{
		if ( (this == other ) ) return true;
		if ( !(other instanceof AccountSettingImpl) ) return false;
		AccountSettingImpl castOther = (AccountSettingImpl)other; 

		return new EqualsBuilder()
			.append(this.getTag(), castOther.getTag())
			.append(this.getValue(), castOther.getValue())
			.isEquals();
	}


	public int hashCode() 
	{
		return new HashCodeBuilder()
		.append(this.getTag())
		.append(this.getValue())
		.toHashCode();
	}

}
