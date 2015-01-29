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
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

import org.janux.bus.persistence.Persistent;
import org.janux.util.JanuxToStringStyle;

/**
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.8 $ - $Date: 2007-01-11 23:13:10 $
 */
public class RoleImpl extends AuthorizationHolderBase implements Persistent, Role, java.io.Serializable
{
	private static final long serialVersionUID = 2012032701;
	private String  description;
	private Integer sortOrder;
	private boolean enabled = true;

	private List<PermissionGranted> permissionsGrantedList;
	private Map<PermissionGrantedKey, Long> permissionsGranted;
	private List<Role> roles;

	protected Integer id = new Integer(Persistent.UNSAVED_ID);

	/** plain vanilla constructor */
	public RoleImpl() {}

	public RoleImpl(String name) {
		this(name, null, null, null);
	}

	public RoleImpl(String name, String description) {
		this(name, description, null, null);
	}

	public RoleImpl(String name, String description, List<Role> roles, Map<PermissionGrantedKey, Long> permissionsGranted) 
	{
		super(name, roles, permissionsGranted);
		this.setDescription(description);
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}



	/*
	protected List<PermissionGranted> getPermissionsGrantedList() {
		return this.permissionsGrantedList;
	}
	
	protected void setPermissionsGrantedList(List<PermissionGranted> permissionsGrantedList) {
		this.permissionsGrantedList = permissionsGrantedList;
	}
	*/

	protected Map<PermissionGrantedKey, Long> getPermissionsGranted() {

		if (this.permissionsGranted == null)
			this.permissionsGranted = new HashMap<PermissionGrantedKey, Long>();

		return this.permissionsGranted;
	}
	
	protected void setPermissionsGranted(Map<PermissionGrantedKey, Long> permissionsGranted) {
		this.permissionsGranted = permissionsGranted;
	}


	/*
	public Set<String> getPermissionContextStrings() {
		return this.getPermissionsManager().getPermissionContextStrings();
	}
	*/

	public Integer getSortOrder() {
		return this.sortOrder;
	}
	
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}


	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String toString() 
	{ 
		ToStringBuilder sb = new ToStringBuilder(this, JanuxToStringStyle.COMPACT);

		sb.append("id", getId())
			.append("name", getName())
			.append("enabled", isEnabled());

		if (getSortOrder() != null) sb.append("sortOrder", getSortOrder());

		sb.append("permsGranted", getPermissionsGranted());

		if (this.getRoles().size() > 0) sb.append("roles", getRoles());
		
		return sb.toString();
	}


	public boolean equals(Object other)
	{
		if ( (this == other ) ) return true;
		if ( !(other instanceof RoleImpl) ) return false;
		Role castOther = (Role)other; 

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


} // end class RoleImpl
