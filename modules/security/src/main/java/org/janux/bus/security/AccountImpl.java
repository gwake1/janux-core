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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.janux.bus.persistence.Persistent;
import org.janux.util.JanuxToStringStyle;

public class AccountImpl extends AuthorizationHolderBase
implements Persistent, Account, java.io.Serializable
{
	private static final long serialVersionUID = 2012032001;
	private String    name;
	private String    password;
	private Date      expire;
	private Date      expirePassword;
	private boolean   nonLocked;
	private boolean   enabled = true;

	private List<Role> roles;
	private Set<AccountSetting> settings;
	private List<PermissionGranted> permissionsGrantedList;
	private AuthorizationHolderBase permsManager;

	protected Integer id = new Integer(Persistent.UNSAVED_ID);

	/** plain vanilla constructor */
	public AccountImpl() {}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return this.getName();
	}
	
	public void setUsername(String name) {
		this.setName(name);
	}


	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	public Date getExpiration() {
		return this.expire;
	}

	public void setExpiration(Date date) {
		this.expire = date;
	}


	public Date getPasswordExpiration() {
		return expirePassword;
	}

	public void setPasswordExpiration(Date date) {
		this.expirePassword = date;
	}


	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public boolean isAccountNonLocked() {
		return this.nonLocked;
	}

	public void setAccountNonLocked(boolean b) {
		this.nonLocked = b;
	}


	public boolean isAccountNonExpired() 
	{
		if (this.getExpiration() != null)
			return this.getExpiration().after(new Date());
		else
			return true;
	}


	public boolean isCredentialsNonExpired()
	{
		if (this.getPasswordExpiration() != null)
			return this.getPasswordExpiration().after(new Date());
		else
			return true;
	}


	

	public String toString() 
	{
		ToStringBuilder sb = new ToStringBuilder(this, JanuxToStringStyle.COMPACT);

		sb.append("id", getId())
			.append("name", getName())
			.append("enabled", isEnabled())
			.append("nonLocked", isAccountNonLocked())
			.append("expire", getExpiration())
			.append("expirePass", getPasswordExpiration());

		if (this.getRoles().size() > 0) sb.append("roles", getRoles());

		return sb.toString();
	}


	/** two accounts are equal if they have the same name */
	public boolean equals(Object other)
	{
		if ( (this == other ) ) return true;
		if ( !(other instanceof AccountImpl) ) return false;
		Account castOther = (Account)other; 

		return new EqualsBuilder()
			.append(this.getName(), castOther.getName())
			.isEquals();
	}


	public int hashCode() 
	{
		return new HashCodeBuilder()
		.append(this.getName())
		.toHashCode();
	}


	public int compareTo(Object other) {
		if ( (this == other ) ) return 0;
		if ( !(other instanceof AccountImpl) ) return 1;
		Account castOther = (Account)other; 

		return new CompareToBuilder()
			.append(this.getName(), castOther.getName())
			.toComparison();
	}


	public Set<AccountSetting> getSettings() {
		if (settings == null)
		{
			settings = new HashSet<AccountSetting>();
		}
		
		return settings;
	}


	public void setSettings(Set<AccountSetting> settings) {
		this.settings = settings;
	}


} // end class AccountImpl
