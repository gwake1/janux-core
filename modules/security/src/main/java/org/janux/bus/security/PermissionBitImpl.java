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

import org.janux.util.CompUtils;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionBitImpl implements PermissionBit, java.io.Serializable
{

	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final long serialVersionUID = -4731199333208246315L;
	private String name;
	private short	position = -1;

	private String description;
	private PermissionContext permContext;
	private Integer sortOrder;


	/** plain vanilla constructor */
	public PermissionBitImpl() {}

	public PermissionBitImpl(String name, String description) 
	{
		if (CompUtils.isNull(name)) {
			String msg = "The name of a PermissionBit must be non-null and contain at least one non-white space character";
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}

		this.setName(name);
		this.setDescription(description);
	}

	public PermissionBitImpl(String name) 
	{
		this(name, null);
	}


	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public short getPosition() {
		return this.position;
	}


	public void setPosition(short position) {
		this.position = position;
	}


	public long getValue() {
		return (long)Math.pow(2.0, this.getPosition());
	}


	public PermissionContext getPermissionContext() {
		return this.permContext;
	}


	public void setPermissionContext(PermissionContext bitmask) {
		this.permContext = bitmask;
	}


	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getSortOrder() 
	{
		if (this.sortOrder == null)
			this.sortOrder = new Integer(this.getPosition());

		return this.sortOrder;
	}

	public void setSortOrder(Integer i) {
		this.sortOrder = i;
	}
	
	
	public String toString() 
	{
		return new ToStringBuilder(this)
			.append("name", getName())
			.append("position", getPosition())
			.append("sortOrder", getSortOrder())
			.toString();
	}


	/** 
	 * two PermissionBit objects are equal if they have the same position 
	 * and are defined by the same PermissionContext
	 */
	public boolean equals(Object other)
	{
		if ( (this == other ) ) return true;
		if ( !(other instanceof PermissionBitImpl) ) return false;
		PermissionBit castOther = (PermissionBit)other; 

		return new EqualsBuilder()
			.append(this.getPermissionContext(), castOther.getPermissionContext())
			.append(this.getPosition(), castOther.getPosition())
			.isEquals();
	}


	public int hashCode() 
	{
		return new HashCodeBuilder()
		.append(this.getPermissionContext())
		.append(this.getPosition())
		.toHashCode();
	}

} // end class PermissionBitImpl
