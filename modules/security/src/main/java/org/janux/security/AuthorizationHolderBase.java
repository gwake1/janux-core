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

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

import org.janux.security.metadata.*;
import org.janux.util.Chronometer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 ***************************************************************************************************
 * Implementation convenience class that factors in one class the functions
 * that derive the permissions that a Role or Account may have based on its
 * associated roles and permissions granted 
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.8 $ - $Date: 2007-12-27 00:51:17 $
 ***************************************************************************************************
 */
public class AuthorizationHolderBase implements AuthorizationHolder, Serializable
{
	private static final long serialVersionUID = 20070617L;

	protected transient Logger log = LoggerFactory.getLogger(this.getClass());

	protected String name;
	protected List<Role> roles;
	protected boolean isSuper;

	protected Map<String, AuthorizationContext> authContexts;

	/** this is declared as protected simply for testing purposes */
	protected Map<PermissionGrantedKey, Long> permissionsGranted;

	private Map<String, Long> permsUnionMap;

	public AuthorizationHolderBase() {}

	public AuthorizationHolderBase(String name) {
		this(name, null, null);
	}

	public AuthorizationHolderBase(String name, List<Role> roles, Map<PermissionGrantedKey, Long> permissionsGranted) 
	{
		this.name = name;
		this.roles = roles;
		this.permissionsGranted = permissionsGranted;
	}


	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public List<Role> getRoles() {
		if (this.roles == null) { this.roles = new ArrayList<Role>(); }
		return this.roles;
	}
	
	public void setRoles(List<Role> aggrRoles) {
		this.roles = aggrRoles;
	}



	public Map<String, AuthorizationContext> getAuthorizationContexts()
	{
		if (this.authContexts == null)
		{
			this.authContexts = new HashMap<String, AuthorizationContext>();

			for (Role aggrRole : this.getRoles()) {
				//if (log.isDebugEnabled()) log.debug(this.name + ": adding all permission contexts inherited from aggregated Role " + aggrRole.getName() + ": " + aggrRole.getAuthorizationContexts());
				this.authContexts.putAll(aggrRole.getAuthorizationContexts());
			}

			for (PermissionGrantedKey permGrantedKey : this.getPermissionsGranted().keySet()) {
					//if (log.isDebugEnabled()) log.debug(this.name + ": adding native permission context: " + permGranted.getAuthorizationContext().getName());
					this.authContexts.put(permGrantedKey.getAuthorizationContext().getName(), permGrantedKey.getAuthorizationContext());
			}
		}

		return authContexts;
	}

	public boolean can(long requiredPerms, String authContextName)
	{ 
		// TODO: improve this so that the denied permissions are applied on 'top' of
		// a super user: i.e. grant All Perms except A,B,C.
		if (this.isSuper()) {
			return true;
		} else {
			if (requiredPerms < 1) 
			{
				// String msg = this.name + ".hasPermissions(" + authContextName + ", " + requiredPerms + "): when querying for permissions you must provide a permission strictly greater than 0. ";
				String msg = String.format("%s.can(%s,%s): when querying for permissions you must provide a permission strictly greater than 0.", this.name, authContextName, requiredPerms);
				log.error(msg);
				throw new IllegalArgumentException(msg);
			}
				
			return (this.getPermissionsValue(authContextName) & requiredPerms) == requiredPerms;
		}
	}

	public boolean hasPermissions(String authContextName, long requiredPerms) {
		return this.can(requiredPerms, authContextName);
	}


	public boolean can(String[] permNames, String authContextName)
	{
		long requiredPerms = 0;
		AuthorizationContext authContext = this.getAuthorizationContexts().get(authContextName);

		if (authContext == null) { // no permissions have been granted in that context
			return false;
		} else {
			requiredPerms = authContext.getPermissionsAsNumber(permNames);
			return this.can(requiredPerms, authContextName);
		}
	}


	public boolean hasPermissions(String authContextName, String[] permNames) {
		return this.can(permNames, authContextName);
	}


	public boolean can(String permissionName, String authContextName)
	{
		return this.can(new String[] {permissionName}, authContextName);
	}


	public boolean hasPermission(String authContextName, String permissionName) {
		return this.can(permissionName, authContextName);
	}


	public String[] getPermissions(String authContextName)
	{
		Set<String> permGrantedNames = new LinkedHashSet<String>();
		AuthorizationContext authContext = this.getAuthorizationContexts().get(authContextName);
		
		if (authContext != null)
		{
			for ( PermissionBit permBit : authContext.getPermissionBits() )
			{
				// if ( this.hasPermissions(authContextName, permBit.getValue()) )
				if ( this.can( permBit.getValue(), authContextName ) )
					permGrantedNames.add(permBit.getName());
			}
		}
		
		return permGrantedNames.toArray(new String[permGrantedNames.size()]);
	}


	public long getPermissionsValue(String authContextName)
	{
		Long perms = this.getPermsUnionMap().get(authContextName);
		return (perms != null) ? perms : 0;
	}


	public void grant(long permsGranted, AuthorizationContext authContext)
	{
		this.validatePermissions(authContext, permsGranted);

		// if the permission granted is 0, remove the record; note that this will
		// not revoke all permissions for this AuthorizationContext, as there may be
		// other permissions in this AuthorizationContext that are inherited from the
		// Roles of this entity
		if (log.isInfoEnabled())
			log.info("granting permissions '" + permsGranted + "' in AuthorizationContext '" + authContext.getName() + "' to entity '" + this.name + "'");

		if (permsGranted == 0)
			this.getPermissionsGranted().remove(new PermissionGrantedKey(authContext, false));
		else 
			this.setPermissionGranted(authContext, false, permsGranted); 

		// recompute union of inherited and granted permissions
		this.permsUnionMap = null;
	}

	public void grant(String[] permsGranted, AuthorizationContext authContext) {
		this.grant(authContext.getValue(permsGranted), authContext);
	}

	public void grant(String permGranted, AuthorizationContext authContext) {
		this.grant(new String[] {permGranted}, authContext);
	}

	public void grantPermissions(AuthorizationContext authContext, long permsGranted) {
		log.warn("Calling deprecated grantPermissions(AuthorizationContext, long)");
		this.grant(permsGranted, authContext);
	}


	public void deny(long permsDenied, AuthorizationContext authContext)
	{
		this.validatePermissions(authContext, permsDenied);
		// if the permission denied is 0, remove the corresponding isDeny record; 
		if (log.isInfoEnabled())
			log.info("denying permissions " + permsDenied + " in AuthorizationContext '" + authContext.getName() + "' from entity '" + this.name + "'");

		if (permsDenied == 0)
			this.getPermissionsGranted().remove(new PermissionGrantedKey(authContext, true));
		else 
			this.setPermissionGranted(authContext, true, permsDenied); 
	}

	public void deny(String[] permsDenied, AuthorizationContext authContext)
	{
		this.deny(authContext.getPermissionsAsNumber(permsDenied), authContext);
	}

	public void deny(String permDenied, AuthorizationContext authContext)
	{
		this.deny(new String[] {permDenied}, authContext);
	}


	public void denyPermissions(AuthorizationContext authContext, long permsDenied) {
		log.warn("Calling deprecated denyPermissions(AuthorizationContext, long)");
		this.deny(permsDenied, authContext);
	}


	/** @return the permissions granted directly to this AuthorizationHolder, as a Long */
	private Long getPermissionGranted(AuthorizationContext authContext, boolean isDeny)
	{
		PermissionGrantedKey permGrantedKey = new PermissionGrantedKey(authContext, isDeny);
		return this.getPermissionsGranted().get(permGrantedKey);
	}


	/** Grant a permission directly to this Role */
	private void setPermissionGranted(AuthorizationContext authContext, boolean isDeny, Long value)
	{
		PermissionGrantedKey permGrantedKey = new PermissionGrantedKey(authContext, isDeny);
		this.getPermissionsGranted().put(permGrantedKey, value);
	}


	/** 
	 * @return a Map of the permissions held directly by this AuthorizationHolder, 
	 * rather than those inherited from a Role
	 */
	protected Map<PermissionGrantedKey, Long> getPermissionsGranted()
	{
		if (this.permissionsGranted == null)
			this.permissionsGranted = new HashMap<PermissionGrantedKey, Long>();

		return this.permissionsGranted;
	}


	/** 
	 * validates that the perms provided are between 0 and authContext.getMaxValue(),
	 * @throws IllegalArgumentException the perms provided are not between 0 and authContext.getMaxValue(),
	 */
	private void validatePermissions(AuthorizationContext authContext, long perms)
	{
		String msg = null;

		if (authContext == null)
			msg = "Attempting to assign permissions to entity '" + this.name + "' with null AuthorizationContext";

		else if ( perms < 0 ) 
			msg = "Attempting to assign a negative permission bitmask in the context of AuthorizationContext '" + authContext.getName() + "' to entity '" + this.name + "'";

		else if ( perms > authContext.getMaxValue() ) 
			msg = "The permission bitmask that you are trying to assign: '" + perms 
				+ "' has a value greater than the maximum '" + authContext.getMaxValue() 
				+ "' that can be assigned in the context of AuthorizationContext '" + authContext.getName() 
				+ "' to entity '" + this.name + "'";

		if (msg != null) 
		{
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}
	}


	/*
	public Long getPermissionsGranted(String authContextName) 
	{
		throw new UnsupportedOperationException(
				"getPermissionsGranted(authContextName) has not yet been implemented");
		//return this.getPermissionsGranted().get(new AuthorizationContext(authContextName));
	}
	*/
	

	/*
	public void setPermissionsGranted(String authContextName, Long permissions) 
	{
		throw new UnsupportedOperationException(
				"setPermissionsGranted(authContextName, permissions) has not yet been implemented");
	}
	*/

	

	/** 
	 * Iterates over all aggregated Roles and, for each AuthorizationContext, 
	 * calculates the union of all Permissions granted; 
	 * this is where the heavy lifting of this bitmask-based implementation occurs
	 */
	private Map<String, Long> getPermsUnionMap()
	{
		if (permsUnionMap == null)
		{
			Chronometer timer = new Chronometer(true);
			permsUnionMap = new HashMap<String, Long>();

			// flatten permissions of all aggregated Roles, if any
			if (!this.getRoles().isEmpty())
			{
				if (log.isDebugEnabled()) 
				{
					// pre-eventively gather all permission context names to yield cleaner debug logs
					log.debug(this.name + ": aggregating permissions from aggregated Roles for permission contexts: " + this.getAuthorizationContexts().keySet());
				}

				for (Role aggrRole : this.getRoles())
				{
					//if (log.isDebugEnabled()) log.debug("begin iterating over " + aggrRole.getName());
					for (String context : aggrRole.getAuthorizationContexts().keySet())
					{
						long permsUnion = (permsUnionMap.get(context) != null) ? permsUnionMap.get(context).longValue() : 0;
						long aggrRolePerms = aggrRole.getPermissionsValue(context);

						if (log.isDebugEnabled()) {
							log.debug(this.name + "[" + context + "]: permsUnion(" + permsUnion + ") + aggrRolePerms(" + aggrRolePerms + ") =  newPermsUnion(" + (permsUnion | aggrRolePerms) + ")");
						}

						permsUnionMap.put(context, new Long(permsUnion | aggrRolePerms));
					}
					//if (log.isDebugEnabled()) log.debug("end iterating over " + aggrRole.getName());
				}
			}


			if (!this.getPermissionsGranted().isEmpty())
			{
				if (log.isDebugEnabled()) log.debug(this.name + ": aggregating native permissions: " + this.getPermissionsGranted());

				for (PermissionGrantedKey permGrantedKey : this.getPermissionsGranted().keySet())
				{
					String context = permGrantedKey.getAuthorizationContext().getName();

					long inheritedPerms = (permsUnionMap.get(context) != null) ? permsUnionMap.get(context).longValue() : 0;
					//long permGranted    = permissionsGranted.get(permGrantedKey).getPermissions();
					long permGranted    = this.getPermissionsGranted().get(permGrantedKey);

					// substract or add the permission
					long permsUnion = permGrantedKey.isDeny() ?  (inheritedPerms - (permGranted & inheritedPerms)) : inheritedPerms | permGranted;

					if (log.isDebugEnabled()) 
					{
						String op = permGrantedKey.isDeny() ? "-" : "+";
						log.debug(this.name + "[" + context + "]: inheritedPerms(" + inheritedPerms + ") " + op + " permGranted(" + permGranted + ") =  permsUnion(" + permsUnion + ")");
					}

					permsUnionMap.put(context, permsUnion);
				}
			}
			if (log.isDebugEnabled()) log.debug(this.name + ": calculated permissions for role in " + timer.printElapsedTime());
		}

		return this.permsUnionMap;
	}

	
	public boolean isSuper() {
		return this.isSuper;
	}

	public void setSuper(boolean isSuper) {
		log.warn(String.format("Setting %s to Super User!", this.name));
		this.isSuper = isSuper;
	}


	public boolean isAlmighty() {
		return this.isSuper();
	}

	public void setAlmighty(boolean isAlmighty) {
		this.setSuper(isAlmighty);
	}


} // end class


