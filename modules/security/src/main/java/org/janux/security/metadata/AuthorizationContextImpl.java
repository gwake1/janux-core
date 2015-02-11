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

package org.janux.security.metadata;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.janux.util.CompUtils;

public class AuthorizationContextImpl implements AuthorizationContext, java.io.Serializable
{
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1;

	private Integer id;
	private String name;
	private String description;
	private Integer sortOrder;
	private boolean enabled;
	private List<PermissionBit> bits;
	private Map<String, PermissionBit> bitMap;


	/** plain vanilla constructor */
	public AuthorizationContextImpl() {}

	public AuthorizationContextImpl(String name, String description) {
		if (CompUtils.isNull(name)) {
			String msg = "The name of a AuthorizationContext must be non-null and contain at least one non-white space character";
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		this.name = name;
		this.description = description;
	}
 
	public AuthorizationContextImpl(String name) {
		this(name, null);
	}
 
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public List<PermissionBit> getPermissionBits() 
	{
		if (this.bits == null)
			this.bits = new ArrayList<PermissionBit>();

		return this.bits;
	}
	
	protected void setPermissionBits(List<PermissionBit> permissionBits) {
		this.bits = permissionBits;
	}


	public PermissionBit getPermissionBit(String name) {
		return this.getBitMap().get(name);
	}


	public void addPermissionBit(PermissionBit permBit) 
	{
		if (this.getPermissionBit(permBit.getName()) != null) 
		{
			String msg = "A permission bit with name: '" + permBit.getName() + "' already exists in AuthorizationContext: " + this;
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (this.getMaxBitPosition() > 63) 
		{
			String msg = "This implementation of a AuthorizationContext does not accept more than 64 PermissionBits; you are trying to add 65 PermissionBits to AuthorizationContext: " + this;
			log.error(msg);
			throw new UnsupportedOperationException(msg);
		}

		permBit.setPosition( (short)(this.getMaxBitPosition() + 1) );
		permBit.setAuthorizationContext(this);
		this.getPermissionBits().add(permBit);

		// force refresh of bitMap on next invocation of getBitMap
		this.bitMap = null;
	}


	public long getPermissionsAsNumber(String[] permBitNames) 
	{
		long permsValue = 0;

		for (String permName : permBitNames)
		{
			if ( this.getPermissionBit(permName) != null ) {
				permsValue += this.getPermissionBit(permName).getValue();
			} else {
				String msg = "The permission '" + permName + "' is not defined in the Permission Context " + this;
				log.error(msg);
				throw new IllegalArgumentException(msg);
			}
		}

		return permsValue;
	}


	public long getValue(String[] permBitNames) {
		return this.getPermissionsAsNumber(permBitNames);
	}


	public long getMaxValue() {
		return (long)Math.pow(2.0, this.getPermissionBits().size()) - 1;
	}


	/* Human readable description of this PermissionBit Set */
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}
	
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}


	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean visible) {
		this.enabled = visible;
	}


	/**	   
	 * The set of permissions that this PermissionBit Set defines; note that this represents meta
	 * information of what sort of Permissions are available to be assigned within the context of a
	 * Busines Context and a Role, but that a PermissionBit Set does not confer any of these Permissions
	 * per-se to any entity.
	 */
	protected Map<String, PermissionBit> getBitMap() 
	{
		if (this.bitMap == null) {
			this.bitMap = new HashMap<String, PermissionBit>();
			for (PermissionBit bit : this.getPermissionBits())
				this.bitMap.put(bit.getName(), bit);
		}
		return this.bitMap;
	}

	/** 
	 * returns the highest sequential bit position of all the bits in the permissionBit List, 
	 * or -1 if this AuthorizationContext has no PermissionBits assigned to it; the
	 * value returned by this method should be equal to (getPermissionBits().size() - 1)
	 * but we expressly iterate through the permission bits and assert that fact
	 */
	private short getMaxBitPosition()
	{
		int maxBitPos = -1;
		for (PermissionBit permBit : this.getPermissionBits())
			maxBitPos = Math.max(permBit.getPosition(), maxBitPos);

		if ( maxBitPos != (this.getPermissionBits().size() - 1) )
		{
			String msg = "The highest bit position is not equal to (permissionBits.size - 1) in AuthorizationContext: " + this; log.error(msg);
			throw new IllegalStateException(msg);
		}
		
		return (short)maxBitPos;
	}


	public String toString() 
	{
		return new ToStringBuilder(this)
			.append("id", getId())
			.append("name", getName())
			.append("enabled", isEnabled())
			.append("sortOrder", getSortOrder())
			.append("bits", getPermissionBits())
			.toString();
	}


	/** 
	 * Implements business identity equality: two AuthorizationContexts are equal if they have the same name 
	 */
	public boolean equals(Object other)
	{
		if ( (this == other ) ) return true;
		if ( !(other instanceof AuthorizationContextImpl) ) return false;
		AuthorizationContext castOther = (AuthorizationContext)other; 

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

} // end class AuthorizationContextImpl
