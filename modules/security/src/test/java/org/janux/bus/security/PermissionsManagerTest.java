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

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 ***************************************************************************************************
 *
 *
 * @author  <a href="mailto:philippe.paravicini@eCommerceStudio.com">Philippe Paravicini</a>
 * @version $Revision: 1.1 $ - $Date: 2006-12-07 01:55:18 $
 *
 * @see
 ***************************************************************************************************
 */
public class PermissionsManagerTest extends TestCase
{
	Log log = LogFactory.getLog(this.getClass());
	//static Log log = LogFactory.getLog(FooSetup.class);
	
	private MockObjectFactory mock = new MockObjectFactory();

	static final String PERM_MAN = "aPermissionManager";
	
	private PermissionContext  workContext = mock.getPermissionContext(mock.CTX_WORK);
	private String WORK = workContext.getName();
	private PermissionsManager permsMan    = new PermissionsManager(PERM_MAN);


	public PermissionsManagerTest() {
		super();
	}

	public PermissionsManagerTest(String name) {
		super(name);
	}

	/** executed prior to each test */
	protected void setUp() 
	{ 
	}

	/** executed after each test */
	protected void tearDown() { }

	public void testGrantPermissions()
	{
		permsMan.grantPermissions(workContext, workContext.getMaxValue());
		assertTrue(permsMan.can(workContext.getMaxValue(), WORK));
		assertTrue(permsMan.hasPermissions(WORK, workContext.getMaxValue()));

		for (PermissionBit permBit : workContext.getPermissionBits())
		{
			log.debug("testing hasPermissions '" + permBit.getName() + "': " + permBit.getValue());
			log.debug("testing can '" + permBit.getValue() + "': " + permBit.getName());

			assertTrue(permsMan.can( permBit.getValue(), WORK ));
			assertTrue(permsMan.can( new String[] {permBit.getName()}, WORK ));
			assertTrue(permsMan.can( permBit.getName(), WORK ));

			assertTrue(permsMan.hasPermissions( WORK, permBit.getValue() ));
			assertTrue(permsMan.hasPermissions( WORK, new String[] {permBit.getName()} ));
			assertTrue(permsMan.hasPermission(  WORK, permBit.getName() ));
		}

		// test that when we set a permission of 0 we remove the permission record
		permsMan.grantPermissions(workContext, 0);
		assertNull(permsMan.permissionsGranted.get(new PermissionGrantedKey(workContext, false)));

		try { 
			log.debug("Test null permissionContext");
			permsMan.grantPermissions(null, 0);
			fail("null permissionContexts are not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}

		try { 
			log.debug("Test negative permission value Failure");
			permsMan.grantPermissions(workContext, -1);
			fail("negative permissions are not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}

		try { 
			log.debug("Test maximum permission Failure");
			permsMan.grantPermissions(workContext, workContext.getMaxValue() + 1);
			fail("permissions outside the range are not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}
	}

	/** 
	 * tests a PermissionsManager that does not have Roles attached to it (and
	 * hence no inherited Permissions
	 */
	public void testHasPermissionsWithoutRoles()
	{
		permsMan.grantPermissions(workContext, workContext.getMaxValue());

		assertTrue(permsMan.can( workContext.getMaxValue(), WORK));
		assertTrue(permsMan.hasPermissions(WORK, workContext.getMaxValue()));

		for (PermissionBit permBit : workContext.getPermissionBits())
		{
			log.debug("testing hasPermissions '" + permBit.getName() + "': " + permBit.getValue());
			log.debug("testing can '" + permBit.getValue() + "': " + permBit.getName());
			assertTrue(permsMan.can( permBit.getValue(), WORK ));
			assertTrue(permsMan.hasPermissions( WORK, new String[] {permBit.getName()} ));
		}

		String[] READ          = {mock.PERM_READ};
		String[] READ_UPDATE   = {mock.PERM_READ, mock.PERM_UPDATE};
		String[] READ_DISABLE  = {mock.PERM_READ, mock.PERM_DISABLE};

		permsMan.grantPermissions(workContext, workContext.getValue(READ));

		assertTrue(permsMan.can(  workContext.getValue(READ), WORK ));
		assertTrue(permsMan.can(  READ, WORK ));

		assertFalse(permsMan.can( workContext.getValue(READ_UPDATE), WORK ));
		assertFalse(permsMan.can( READ_UPDATE, WORK ));


		assertTrue(permsMan.hasPermissions(  WORK, workContext.getValue(READ) ));
		assertTrue(permsMan.hasPermissions(  WORK, READ ));

		assertFalse(permsMan.hasPermissions( WORK, workContext.getValue(READ_UPDATE) ));
		assertFalse(permsMan.hasPermissions( WORK, READ_UPDATE ));

		for (int i=0; i < READ.length ; i++) {
			assertEquals( READ[i], permsMan.getPermissions(WORK)[i] );
		}

		permsMan.grantPermissions(workContext, workContext.getValue(READ_UPDATE));

		assertTrue(permsMan.can(  workContext.getValue(READ), WORK ));
		assertTrue(permsMan.can(  READ, WORK ));

		assertTrue(permsMan.hasPermissions(  WORK, workContext.getValue(READ) ));
		assertTrue(permsMan.hasPermissions(  WORK, READ ));

		for (int i=0; i < READ.length ; i++) {
			assertEquals( READ_UPDATE[i], permsMan.getPermissions(WORK)[i] );
		}

		assertTrue(permsMan.can(  workContext.getValue(READ_UPDATE), WORK ));
		assertTrue(permsMan.can(  READ_UPDATE, WORK ));

		assertFalse(permsMan.can( workContext.getValue(READ_DISABLE), WORK ));
		assertFalse(permsMan.can( READ_DISABLE, WORK ));

		assertTrue(permsMan.hasPermissions(  WORK, workContext.getValue(READ_UPDATE) ));
		assertTrue(permsMan.hasPermissions(  WORK, READ_UPDATE ));

		assertFalse(permsMan.hasPermissions( WORK, workContext.getValue(READ_DISABLE) ));
		assertFalse(permsMan.hasPermissions( WORK, READ_DISABLE ));

		try { 
			log.debug("Test querying for 0 perms failure");
			permsMan.can(0, WORK);
			fail("querying for 0 perms not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}

		try { 
			log.debug("Test querying for negative perms failure");
			permsMan.can(-1, WORK);
			fail("querying for neg perms not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}
	}

} // end class PermissionsManagerTest
