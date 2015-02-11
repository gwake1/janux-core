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

import java.util.Map;
import org.janux.security.metadata.*;

/**
 *************************************************************************************************
 * This interface defines classes that may have permissions granted to them; as of this
 * writing, it is intended to be a super interface for the Account and Role interfaces, both of
 * which may be assigned Permissions directly.
 *
 * This interface was originally named PermissionsCapable, and was renamed in version 0.5. It still
 * extends PermissionsCapable, an empty interface, for backward-compatibility, and this dependency
 * will be removed in a future release.
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.5.0 (renamed in 0.5 from PermissionsCapable, which dates from 0.1)
 *************************************************************************************************
 */
@SuppressWarnings("deprecation")
public interface AuthorizationHolder extends PermissionsCapable
{

	/** 
	 * Returns a map of all the AuthorizationContexts in which this AuthorizationHolder Entity has been
	 * granted a Permission, whether directly or via a Sub-Role; the map is keyed by the
	 * AuthorizationContext's Name
	 */
	Map<String, AuthorizationContext> getAuthorizationContexts();


	/** 
	 * Given a AuthorizationContext, this method returns the permissions, represented as Strings, that
	 * this AuthorizationHolder Entity has in that permission context, or an empty array if the
	 * AuthorizationHolder Entity has no permissions in that AuthorizationContext
	 */
	String[] getPermissions(String authorizationContext);


	/** 
	 * Given a permission context, and the names of permissions available in that context, 
	 * this method returns true if this role has all the permissions named
	 *
	 * @since 0.5.0
	 */
	boolean can(String[] permissionNames, String authorizationContext);


	/** 
	 * Given a permission context, and the name of a permission available in that context, 
	 * this method returns true if this role has the permission named
	 *
	 * @since 0.5.0
	 */
	boolean can(String permissionName, String authorizationContext);


	/** 
	 * In the case of an implementation that uses bitmasks to store permissions, and given a
	 * permission context and a long value representing multiple permissions available in that
	 * context, this method returns true if this role has all the permissions indicated
	 *
	 * @since 0.5.0
	 */
	boolean can(long permissionsValue, String authorizationContext);


	/**  @deprecated as of 0.5.0 use {@link can(String[], String)} instead */
	boolean hasPermissions(String authorizationContext, String[] permissionNames);


	/**
	 * @deprecated as of 0.5.0 use {@link can(String, String)} instead, but note that the
	 * parameter order in that method is inverted
	 */
	boolean hasPermission(String authorizationContext, String permissionName);


	/**
	 * @deprecated as of 0.5.0 use {@link can(long, String)} instead
	 */
	boolean hasPermissions(String authorizationContext, long permissionsValue);


	/** 
	 * In the case of an implementation that uses bitmasks to store permissions, and given a
	 * AuthorizationContext, this method returns the permissions that this AuthorizationHolder Entity has
	 * in that permission context, represented as a long value
	 */
	long getPermissionsValue(String authorizationContext);


	/** 
	 * Given a permission context and a string array representing multiple permissions available in
	 * that context, this method grants the permissions indicated to this AuthorizationHolder Entity.
	 * <p>
	 * The permissions granted by this method replace any direct permissions that the AuthorizationHolder may
	 * already have in this AuthorizationContext, and are in addition to any permissions that this entity
	 * may inherit from its Roles.  If you would like to remove all Permissions granted directly to
	 * this entity within a Permission Context, pass an empty or null array.
	 * </p><p>
	 * Note that passing an empty or null array will not work to revoke permissions that this
	 * entity may have inherited from its Roles; in such case you should call {@link #deny} to
	 * explicitly deny the permissions in question.
	 * </p>
	 *
	 * @see #deny(String[], AuthorizationContext)
	 *
	 * @param authorizationContext a valid AuthorizationContext
	 * @param permissionsGranted
	 * 	an array of strings representing permissions that are to be granted to this AuthorizationHolder
	 * 	Entity, for example ["READ","UPDATE"]; the permissions must be available in the named AuthorizationContext
	 */
	void grant(String[] permissionsGranted, AuthorizationContext authorizationContext);


	/** 
	 * Same as {@link #grant(String[], AuthorizationContext)}
	 * but takes a single permission as an argument, rather than an array 
	 */
	void grant(String permissionGranted, AuthorizationContext authorizationContext);


	/** 
	 * In the case of an implementation that uses bitmasks to store permissions, and given a
	 * permission context and a long value representing multiple permissions available in that
	 * context, this method grants the permissions indicated to this AuthorizationHolder Entity.
	 * <p>
	 * Note that it's more explicit to use 
	 * {@link #grant(String[], AuthorizationContext)}, and possibly
	 * safer since some implementations may not use bitmasks
	 * </p>
	 *
	 * @see #grant(String[],AuthorizationContext)
	 * @see #deny
	 *
	 * @param authorizationContext a valid AuthorizationContext
	 * @param permissionsValue
	 * 	a long value representing permissions that are to be granted to this AuthorizationHolder Entity; 
	 * 	the permissions must be available in the named AuthorizationContext
	 */
	void grant(long permissionsValue, AuthorizationContext authorizationContext);

	/** @deprecated since 0.5.0, use {@link #grant(long, AuthorizationContext)} instead */
	void grantPermissions(AuthorizationContext authorizationContext, long permissionsValue);

	/**
	 * Explicitly denies a set of Permissions within a AuthorizationContext; this method should be used
	 * only to deny permissions that are inherited from Roles associated to this AuthorizationHolder
	 * entity; this method is not meant to be used as the opposite action to method 
	 * {@link #grant(long, AuthorizationContext)}, although it could be abused that way,
	 * <p>
	 * For example, assuming a Role 'PRODUCT ADMIN' that has the Permissions READ, UPDATE, CREATE, DISABLE,
	 * PURGE in the PRODUCT Permission Context (plus possibly other Permissions in other Permission
	 * Contexts), it may desireable to create a 'PRODUCT MANAGER' Role that has 'PRODUCT ADMIN' as its
	 * sub-role, but denies the Permissions to CREATE and PURGE.
	 * </p><p>
	 * <p>
	 * On the other hand, assume that we only have the 'PRODUCT ADMIN' Role that does not aggregate
	 * any other Roles, and that we want to revoke its 'PURGE' Permission, in the Permission Context
	 * 'PRODUCT'.  We could call 'deny' to do so, but this will create an 'isDeny' bitmask in
	 * addition to the existing 'allow' bitmask one through which the 'PURGE' Permission was
	 * originally granted. Instead, it would be simpler to call {@link #grant(long, AuthorizationContext)} 
	 * again with the proper 'allow' bitmask that no longer enables the 'PURGE' permission.  
	 * </p>
	 */
	void deny(String[] permissionsDenied, AuthorizationContext authorizationContext);

	/** 
	 * Same as {@link #deny(String[], AuthorizationContext)} 
	 * but takes a single permission as an argument, rather than an array 
	 */
	void deny(String permissionDenied, AuthorizationContext authorizationContext);

	/**
	 * Equivalent to {@link #deny(String[], AuthorizationContext)} in implementations that use
	 * bitmasks to define permissions
	 *
	 * @see #deny(String[], AuthorizationContext)
	 */
	void deny(long permissionsDenied, AuthorizationContext authorizationContext);

	/** @deprecated since 0.5.0, use {@link #deny(long, AuthorizationContext)} instead */
	void denyPermissions(AuthorizationContext authorizationContext, long permissionsDenied);


	/** If true, this Role is a super user with all Permissions */
	boolean isSuper();

	/** set/unset whether this AuthorizationHolder is a super user with all Permissions */
	void setSuper(boolean isSuper);


	/** Alias for {@link #isSuper} */
	boolean isAlmighty();

	/** Alias for {@link #setSuper} */
	void setAlmighty(boolean isAlmighty);
}
