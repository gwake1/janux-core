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
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.janux.security.metadata.*;

/**
 ***************************************************************************************************
 * Creates an Authorization Schema programmatically that can be used for testing; by Authorization
 * Schema we mean a set of AuthorizationContexts that define the Permissions that may be granted
 * within an application, as well a set of Roles that are granted these permissions in various ways.
 *
 * @author  <a href="mailto:pparavicini@janux.org">Philippe Paravicini</a>
 * @since 0.5.0
 ***************************************************************************************************
 */
public class MockAuthorizationSchema
{
 Logger log = LoggerFactory.getLogger(this.getClass());

	public static final String READ   = "READ";
	public static final String UPDATE = "UPDATE";
	public static final String CREATE = "CREATE";
	public static final String DELETE = "DELETE";
	public static final String PURGE  = "PURGE";

	public static final String VIEW   = "VIEW";
	public static final String START  = "START";
	public static final String STOP   = "STOP";
	public static final String ROTATE = "ROTATE";

	public static final String WIDGET    = "WIDGET";
	public static final String EQUIPMENT = "EQUIPMENT";
	public static final String USER      = "USER";
	public static final String CAMERA    = "CAMERA";

	public static final String WIDGET_DESIGNER    = "WIDGET_DESIGNER";
	public static final String FACILITY_MANAGER   = "FACILITY_MANAGER";
	public static final String PRODUCTION_MANAGER = "PRODUCTION_MANAGER";
	public static final String SYS_ADMIN          = "SYS_ADMIN";

	/** 
	 * Standard permissions in the context of this AuthorizationSchema, may be
	 * something else in the context of a different application
	 */
	public static final String[] STANDARD_PERMS = 
		new String[] {READ, UPDATE, CREATE, DELETE, PURGE};

	/** Name and Description of AuthorizationContexts that will have a standard set of permissions */
	public static final Map<String,String> STANDARD_CONTEXT_DESCRIPTIONS = new HashMap<String,String>() {{
		put(WIDGET,    "Widgets managed by the system");
		put(EQUIPMENT, "Equipment used to produce a Widget");
		put(USER,      "User Records");
	}};

	public static Map<String, AuthorizationContext> authContexts;
	public static Map<String, Role> roles;

	public MockAuthorizationSchema() {
		if (authContexts == null) {
			authContexts = this.createAuthorizationContexts();
		} 

		if (roles == null) {
			roles = this.createRoles();
		} 
	}


	/** Creates a map of AuthorizationContexts programmatically */
	private Map<String, AuthorizationContext> createAuthorizationContexts() 
	{
		Map<String, AuthorizationContext> authContexts = new HashMap<String, AuthorizationContext>();

		for (String name : STANDARD_CONTEXT_DESCRIPTIONS.keySet()) {
			authContexts.put(name, this.createAuthorizationContext(
						name, STANDARD_CONTEXT_DESCRIPTIONS.get(name), STANDARD_PERMS ));
		}

		// add a non-standard AuthorizationContext
		authContexts.put(CAMERA, this.createAuthorizationContext(
					CAMERA, "Defines permissions available to control security cameras", 
					new String[] {VIEW, START, STOP, ROTATE}));

		return authContexts;
	}


	private Map<String, Role> createRoles() 
	{
		Map<String, Role> roles = new HashMap<String,Role>();

		roles.put(WIDGET_DESIGNER,   createWidgetDesigner());
		roles.put(FACILITY_MANAGER,   createFacilityManager());

		roles.put(PRODUCTION_MANAGER, createProductionManager( 
					roles.get(WIDGET_DESIGNER), roles.get(FACILITY_MANAGER)));

		roles.put(SYS_ADMIN, createSuperUser());

		return roles;
	}


	/** CRUD on Widget, READ/UPDATE Equipment, READ Users */
	private Role createWidgetDesigner() 
	{
		Role role = new RoleImpl(WIDGET_DESIGNER, "A Widget Designer");

		role.grantPermissions(
				new String[] {READ, UPDATE, CREATE, DELETE}, authContexts.get(WIDGET));

		role.grantPermissions(
				new String[] {READ, UPDATE}, authContexts.get(EQUIPMENT));

		role.grantPermission(READ, authContexts.get(USER));

		return role;
	}


	/** CRUD Equipment, READ Widget, READ User */
	private Role createFacilityManager() 
	{
		Role role = new RoleImpl(FACILITY_MANAGER, "A Facilities manager");

		role.grantPermissions(
				new String[] {READ, UPDATE, CREATE, DELETE}, authContexts.get(EQUIPMENT));

		role.grantPermission(READ, authContexts.get(WIDGET));
		role.grantPermission(READ, authContexts.get(USER));

		return role;
	}

	/** 
	 * Aggregates WIDGET_DESIGNER and FACILITY_MANAGER Roles, but is denied the
	 * ability to CREATE/DELETE WIDGETS, and CREATE EQUIPMENT or USER entities
	 */
	private Role createProductionManager(Role role1, Role role2) {
		Role role = new RoleImpl(PRODUCTION_MANAGER, "A Production manager");

		role.getRoles().add(role1);
		role.getRoles().add(role2);

		role.denyPermissions(new String[] {CREATE, DELETE}, authContexts.get(WIDGET));
		role.denyPermission(CREATE, authContexts.get(EQUIPMENT));

		// test that we can deny a permission that has not been inherited through a
		// Role; in other words, a redundant and unnecessary 'deny' but it should
		// not cause anything to break
		role.denyPermission(CREATE, authContexts.get(USER));

		return role;
	}


	private Role createSuperUser() 
	{
		Role role = new RoleImpl(SYS_ADMIN, "System Administrator with all privileges");
		role.setAlmighty(true);
		return role;
	}


	/** 
	 * Creates a AuthorizationContext with the set of Permission Names passed;
	 * creates a default description for the Permission
	 */
	private AuthorizationContext createAuthorizationContext(String name, String entity, String[] permNames) 
	{
		AuthorizationContext authContext = new AuthorizationContextImpl(
				name, String.format("Defines permissions available on %s", entity));

		for (int i = 0; i < permNames.length; i++) {
			String permName = permNames[i];
			authContext.addPermissionBit(new PermissionBitImpl(permName,
					String.format("Grants permission to %s %s", permName, entity)));
		}

		return authContext;
	}

} // end class MockAuthorizationSchema
