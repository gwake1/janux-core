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


/**
 ***************************************************************************************************
 * Class representing an individual PermissionBit within a specific AuthorizationContext; a PermissionBit is
 * only meaningful in the context of the AuthorizationContext that defines it: for example, a AuthorizationContext
 * named 'PERSON' may define Permissions with names 'CREATE', 'READ', 'UPDATE', 'DISABLE',
 * 'PURGE', that define the kind of operations on Persons that may be restricted by the security
 * system; see the javadoc of AuthorizationContext for a more detailed discussion.
 * <p>
 * The PermissionBit interface provides for defining the bit position of the PermissionBit within a bit
 * mask (0, 1, 2, 3, etc...), and a convenience method for returning the long value of that bit
 * position (that is 2 taken to the power of the bitPosition, e.g. 1, 2, 4, 8...)
 * </p>
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.1
 ***************************************************************************************************
 */
public interface PermissionBit
{
	/**	
	 * Short-hand name for this PermissionBit (e.g.: READ), 
	 * unique in the context of the containing AuthorizationContext 
	 */
	String getName();
	void setName(String name);

	/** 
	 * The position of the PermissionBit within the bit mask defined by the AuthorizationContext, should be a
	 * sequential integer relative to the sequence defined by the AuthorizationContext; so if a AuthorizationContext
	 * defines 5 permissions, this should be a number between 0 and 4 that is not used by any of the
	 * other Permissions in the AuthorizationContext
	 */
	short getPosition();
	void  setPosition(short pos);

	/** A convenience method that returns 2 to the power of the bitPosition */
	long getValue();

	/** The AuthorizationContext that contains/uses this PermissionBit */
	AuthorizationContext getAuthorizationContext();
	void setAuthorizationContext(AuthorizationContext authContext);
	
	/**	 Human readable description of this PermissionBit */
	String getDescription();
	void setDescription(String description);

	/** 
	 * used to display the sort order independently from the Bit's Position, defaults to the
	 * getPosition if not set explicitly 
	 */
	Integer getSortOrder();
	void setSortOrder(Integer i);
}
