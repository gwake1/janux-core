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

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author   <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.5.0
 */
public class RoleTest extends TestCase
{
 Logger log = LoggerFactory.getLogger(this.getClass());

	private MockAuthorizationSchema authSchema = new MockAuthorizationSchema();

	private static final String READ   = MockAuthorizationSchema.READ;
	private static final String UPDATE = MockAuthorizationSchema.UPDATE;
	private static final String CREATE = MockAuthorizationSchema.CREATE;
	private static final String DELETE = MockAuthorizationSchema.DELETE;
	private static final String PURGE  = MockAuthorizationSchema.PURGE;

	private static final String VIEW   = MockAuthorizationSchema.VIEW;
	private static final String START  = MockAuthorizationSchema.START;
	private static final String STOP   = MockAuthorizationSchema.STOP;
	private static final String ROTATE = MockAuthorizationSchema.ROTATE;

	private static final String WIDGET    = MockAuthorizationSchema.WIDGET;
	private static final String EQUIPMENT = MockAuthorizationSchema.EQUIPMENT;
	private static final String USER      = MockAuthorizationSchema.USER;
	private static final String CAMERA    = MockAuthorizationSchema.CAMERA;

	public  static final String WIDGET_DESIGNER    = MockAuthorizationSchema.WIDGET_DESIGNER;
	public  static final String FACILITY_MANAGER   = MockAuthorizationSchema.FACILITY_MANAGER;
	public  static final String PRODUCTION_MANAGER = MockAuthorizationSchema.PRODUCTION_MANAGER;
	public  static final String SYS_ADMIN          = MockAuthorizationSchema.SYS_ADMIN;

	public RoleTest() {
		super();
	}

	public RoleTest(String name) {
		super(name);
	}


	public void testFlatRole() {
		Role role = authSchema.roles.get(WIDGET_DESIGNER);

		assertEquals(WIDGET_DESIGNER, role.getName());
		assertEquals("A Widget Designer", role.getDescription());
		assertEquals(0, role.getRoles().size());
		assertTrue(role.isEnabled());

		assertEquals(3, role.getAuthorizationContexts().keySet().size());
		assertEquals(1+2+4+8, role.getPermissionsValue(WIDGET));

		List permsGranted = Arrays.asList(role.getPermissions(WIDGET));

		assertEquals(4, permsGranted.size());

		assertTrue(permsGranted.contains(READ));
		assertTrue(permsGranted.contains(UPDATE));
		assertTrue(permsGranted.contains(CREATE));
		assertTrue(permsGranted.contains(DELETE));

		assertTrue(role.can(READ, WIDGET));
		assertTrue(role.can(1, WIDGET));

		assertTrue(role.can(new String[] {READ, DELETE}, WIDGET));
		assertTrue(role.can(1+8, WIDGET));

		assertTrue(role.can(new String[] {READ, UPDATE, CREATE, DELETE}, WIDGET));
		assertTrue(role.can(1+2+4+8, WIDGET));

		assertFalse(role.can(PURGE, WIDGET));
		assertFalse(role.can(16, WIDGET));
		assertFalse(role.can(new String[] {READ, PURGE}, WIDGET));
		assertFalse(role.can(1+16, WIDGET));

		// Assert that 'can' works properly when no permissions were granted in a context
		assertEquals(0, role.getPermissions(CAMERA).length);
		assertEquals(0, role.getPermissionsValue(CAMERA));
		assertFalse(role.can(VIEW, CAMERA));
		assertFalse(role.can(1, CAMERA));
		assertFalse(role.can(new String[] {VIEW, ROTATE}, CAMERA));
		assertFalse(role.can(1+8, CAMERA));

		assertTrue(role.hasPermission(WIDGET, READ));
		assertTrue(role.hasPermissions(WIDGET, 1));
		assertTrue(role.hasPermissions(WIDGET, new String[] {READ, DELETE}));
		assertTrue(role.hasPermissions(WIDGET, 1+8));
		assertTrue(role.hasPermissions(WIDGET, new String[] {READ, UPDATE, CREATE, DELETE}));
		assertTrue(role.hasPermissions(WIDGET, 1+2+4+8));
		assertFalse(role.hasPermission(WIDGET, PURGE));
		assertFalse(role.hasPermissions(WIDGET, 16));
		assertFalse(role.hasPermissions(WIDGET, new String[] {READ, PURGE}));
		assertFalse(role.hasPermissions(WIDGET, 1+16));
	}


	/** 
	 * Tests a PRODUCTION_MANAGER role which does not have permissions assigned to it
	 * directly, but instead aggregates WIDGET_DESIGNER and EQUIPMENT_MANAGER roles
	 */
	public void testRoleWithAggrRoles()
	{
		Role role = authSchema.roles.get(PRODUCTION_MANAGER);

		assertEquals(PRODUCTION_MANAGER, role.getName());
		assertEquals(2, role.getRoles().size());
		assertTrue(role.isEnabled());

		assertEquals(1+2, role.getPermissionsValue(WIDGET));
		assertTrue(role.can(new String[] {READ, UPDATE}, WIDGET));
		assertFalse(role.can(CREATE, WIDGET));
		assertFalse(role.can(DELETE, WIDGET));

		List permsGranted = Arrays.asList(role.getPermissions(WIDGET));
		assertEquals(2, permsGranted.size());

		assertTrue(permsGranted.contains(READ));
		assertTrue(permsGranted.contains(UPDATE));

		assertEquals(1+2+8, role.getPermissionsValue(EQUIPMENT));
		assertTrue(role.can(new String[] {READ, UPDATE, DELETE}, EQUIPMENT));
		assertFalse(role.can(CREATE, EQUIPMENT));

		assertEquals(1, role.getPermissionsValue(USER));
		assertTrue(role.can(READ, USER));
		assertFalse(role.can(CREATE, USER));
		assertFalse(role.can(UPDATE, USER));
		assertFalse(role.can(DELETE, USER));
	}


	/**
	 * Tests a Role that has permissions inherited via aggregated roles, as well as
	 * additional permissions assigned directly
	 */
	/*
	public void testRoleWithAggrRolesAndPerms()
	{
		AuthorizationHolder role = roleDaoGeneric.findByName(SUPERVISOR);
		assertNotNull(SUPERVISOR, role);

		assertTrue("can take holiday",           role.hasPermissions(CTX_HOL, HOL_PERM_TAKE));
		assertTrue("can approve holiday",        role.hasPermissions(CTX_HOL, HOL_PERM_APPROVE));
		assertTrue("can take & approve holiday", role.hasPermissions(CTX_HOL, HOL_PERM_TAKE + HOL_PERM_APPROVE));
		assertTrue("can declare holiday",        role.hasPermissions(CTX_HOL, HOL_PERM_DECLARE));
		assertTrue("all on holiday",             role.hasPermissions(CTX_HOL, HOL_PERM_TAKE + HOL_PERM_APPROVE + HOL_PERM_DECLARE));

		assertTrue("can create work",  role.hasPermissions(CTX_WORK, WORK_PERM_CREATE));
		assertTrue("can perform work", role.hasPermissions(CTX_WORK, WORK_PERM_PERFORM));
		assertTrue("can assign work",  role.hasPermissions(CTX_WORK, WORK_PERM_ASSIGN));
		assertTrue("can skip work",    role.hasPermissions(CTX_WORK, WORK_PERM_SKIP));
		assertTrue("all on work",      role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM + WORK_PERM_ASSIGN + WORK_PERM_SKIP));
		assertTrue("can create & perform work", role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM));
		assertTrue("can create & assign work",  role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_ASSIGN));
		assertTrue("can perform & assign work", role.hasPermissions(CTX_WORK, WORK_PERM_PERFORM + WORK_PERM_ASSIGN));
		assertTrue("can create, perform & assign work", role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM + WORK_PERM_ASSIGN));
	}
	*/


	/**
	 * Tests a Role that has permissions inherited via multiple levels of aggregated roles, as well as
	 * additional permissions assigned directly
	 */
	/*
	public void testRoleWithMultiLevelAggrRoles()
	{
		AuthorizationHolder role = roleDaoGeneric.findByName(GODDESS);
		assertNotNull(GODDESS, role);

		assertTrue("can take holiday",           role.hasPermissions(CTX_HOL, HOL_PERM_TAKE));
		assertTrue("can approve holiday",        role.hasPermissions(CTX_HOL, HOL_PERM_APPROVE));
		assertTrue("can take & approve holiday", role.hasPermissions(CTX_HOL, HOL_PERM_TAKE + HOL_PERM_APPROVE));
		assertTrue("can declare holiday",        role.hasPermissions(CTX_HOL, HOL_PERM_DECLARE));
		assertTrue("all on holiday",             role.hasPermissions(CTX_HOL, HOL_PERM_TAKE + HOL_PERM_APPROVE + HOL_PERM_DECLARE));

		assertTrue("can create work",  role.hasPermissions(CTX_WORK, WORK_PERM_CREATE));
		assertTrue("can perform work", role.hasPermissions(CTX_WORK, WORK_PERM_PERFORM));
		assertTrue("can assign work",  role.hasPermissions(CTX_WORK, WORK_PERM_ASSIGN));
		assertTrue("can skip work",    role.hasPermissions(CTX_WORK, WORK_PERM_SKIP));
		assertTrue("all on work",      role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM + WORK_PERM_ASSIGN + WORK_PERM_SKIP));
		assertTrue("can create & perform work", role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM));
		assertTrue("can create & assign work",  role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_ASSIGN));
		assertTrue("can perform & assign work", role.hasPermissions(CTX_WORK, WORK_PERM_PERFORM + WORK_PERM_ASSIGN));
		assertTrue("can create, perform & assign work", role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM + WORK_PERM_ASSIGN));
	}
	*/

	/** 
	 * Tests a Role that has permissions inherited from a parent role, 
	 * but explicitly denies one of the Permissions inherited
	 */
	/*
	public void testRoleWithDeny()
	{
		Role role = roleDaoGeneric.findByName(SUPER_HUMAN);
		assertNotNull(SUPER_HUMAN, role);

		AuthorizationHolder aggrRole = role.getRoles().get(0);
		assertNotNull(ENGINEER, aggrRole);

		assertTrue(ENGINEER + " can take holidays", aggrRole.hasPermissions(CTX_HOL, HOL_PERM_TAKE));
		assertTrue(SUPER_HUMAN + " cannot take holidays", !role.hasPermissions(CTX_HOL, HOL_PERM_TAKE));
	}
	*/

	// This should really be within a unit test rather than a DAO test, but for
	// the time being we are putting it here as a matter of convenience
	/*
	private void assertHolidayManager(Role role)
	{
		assertNotNull(role);
		assertNotNull(role.getDescription());

		log.debug("granted perms: " + ((RoleImpl)role).getPermissionsGranted());
		assertEquals("holiday permissions", HOL_PERM_TAKE + HOL_PERM_APPROVE, role.getPermissionsValue(CTX_HOL));
	}

	private void assertWorkManager(AuthorizationHolder role)
	{
		assertNotNull(role);

		assertEquals("work permissions", WORK_PERM_CREATE + WORK_PERM_PERFORM + WORK_PERM_ASSIGN, role.getPermissionsValue(CTX_WORK));

		assertTrue("can create work",  role.hasPermissions(CTX_WORK, WORK_PERM_CREATE));
		assertTrue("can perform work", role.hasPermissions(CTX_WORK, WORK_PERM_PERFORM));
		assertTrue("can assign work",  role.hasPermissions(CTX_WORK, WORK_PERM_ASSIGN));
		assertFalse("can skip work",   role.hasPermissions(CTX_WORK, WORK_PERM_SKIP));
		assertTrue("not all on work",  !role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM + WORK_PERM_ASSIGN + WORK_PERM_SKIP));
		assertTrue("can create & perform work", role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM));
		assertTrue("can create & assign work",  role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_ASSIGN));
		assertTrue("can perform & assign work", role.hasPermissions(CTX_WORK, WORK_PERM_PERFORM + WORK_PERM_ASSIGN));
		assertTrue("can create, perform & assign work", role.hasPermissions(CTX_WORK, WORK_PERM_CREATE + WORK_PERM_PERFORM + WORK_PERM_ASSIGN));
	}
	*/

} // end class RoleTest

