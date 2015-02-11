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

package org.janux.security;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.janux.security.metadata.*;
import org.janux.util.JanuxToStringStyle;

/**
 ***************************************************************************************************
 * This is an implementation class used to represent the key that indexes a Permission Granted map.
 * Generally, when assigning permissions to a AuthorizationHolder entity in the context of a
 * AuthorizationContext, a single bitmask is sufficient to assign a combination of Permissions, and it
 * is not necessary to have multiple bitmasks for the same Permission Context, since, in
 * the case of multiple bitmasks for the same Permission Context, it would be possible to derive an
 * equivalent single bitmask by bitwise 'or-ing' the multiple bitmasks.
 *
 * The exception to this is in the case where a AuthorizationHolder entity (for example a Role)
 * inherits a set of Permissions, and it is desireable to do something like:
 *
 * Add Read/Write permissions to PRODUCT Permission context, but deny the permission to Purge
 *
 * In such case, it is necessary to be able to assign two permission bitmasks: an 'additive'
 * one which adds the desired permissions, and a 'subtractive' one which removes others (a
 * PermissionGranted with the 'isDeny' flag set to true)
 * 
 * Of course, if the AuthorizationHolder entity does not inherit any permissions from a parent entity,
 * one could simply refrain from assigning the undesired permission, rather than having to use an
 * permission bitmask with the 'isDeny' set to true.  
 *
 * Nevertheless, in the case of a AuthorizationHolder entity that inherits other permissions, one may
 * want to make sure that we add to the inherited permissions, as well as be assured that other
 * permissions are denied.  In other to do this, it necessary to assign two PermissionGranted
 * bitmasks, one with isDeny set to false, and one set to true.  Therefore, the key to the
 * permission granted map is the bean herewith which considers both the AuthorizationContext and the
 * isDeny flag.
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.1 $ - $Date: 2006-12-07 01:52:00 $
 ***************************************************************************************************
 */
public class PermissionGrantedKey implements java.io.Serializable
{
	private static final long serialVersionUID = 1;

	private boolean deny = false;
	private AuthorizationContext authContext;

	public PermissionGrantedKey() {}

	public PermissionGrantedKey(AuthorizationContext authContext, boolean isDeny) 
	{
		this.authContext = authContext;
		this.deny = isDeny;
	}

	public AuthorizationContext getAuthorizationContext() {
		return this.authContext;
	}
	
	public void setAuthorizationContext(AuthorizationContext authContext) {
		this.authContext = authContext;
	}


	public boolean isDeny() {
		return this.deny;
	}
	
	public void setDeny(boolean deny) {
		this.deny = deny;
	}


	public String toString() 
	{
		JanuxToStringStyle style = new JanuxToStringStyle();
		style.setUseClassName(false);

		ToStringBuilder sb = new ToStringBuilder(this, style);

		return sb.append("context", getAuthorizationContext().getName())
			.append("isDeny" , isDeny())
			.toString();
	}


	/** 
	 * Two PermissionGranted objects are equal if they refer to the same AuthorizationContext, Role and
	 * have the same value for the isDeny flag - in other words, only one PermissionGranted object may
	 * exist for each Role/AuthorizationContext/isDeny combination
	 */
	public boolean equals(Object other)
	{
		if ( (this == other ) ) return true;
		if ( !(other instanceof PermissionGrantedKey) ) return false;
		PermissionGrantedKey castOther = (PermissionGrantedKey)other; 

		return new EqualsBuilder()
			.append(this.getAuthorizationContext().getName(), castOther.getAuthorizationContext().getName())
			.append(this.isDeny(), castOther.isDeny())
			.isEquals();
	}


	public int hashCode() 
	{
		return new HashCodeBuilder()
		.append(this.getAuthorizationContext().getName())
		.append(this.isDeny())
		.toHashCode();
	}   
} // end class