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
public class AuthorizationHolderTest extends TestCase
{
	Log log = LogFactory.getLog(this.getClass());
	//static Log log = LogFactory.getLog(FooSetup.class);
	
	private MockObjectFactory mock = new MockObjectFactory();

	static final String PERM_MAN = "aPermission";
	
	private PermissionContext  workContext = mock.getPermissionContext(mock.CTX_WORK);
	private String WORK = workContext.getName();
	private AuthorizationHolderBase authHolder    = new AuthorizationHolderBase(PERM_MAN);


	public AuthorizationHolderTest() {
		super();
	}

	public AuthorizationHolderTest(String name) {
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
		authHolder.grantPermissions(workContext, workContext.getMaxValue());
		assertTrue(authHolder.can(workContext.getMaxValue(), WORK));
		assertTrue(authHolder.hasPermissions(WORK, workContext.getMaxValue()));

		for (PermissionBit permBit : workContext.getPermissionBits())
		{
			log.debug("testing hasPermissions '" + permBit.getName() + "': " + permBit.getValue());
			log.debug("testing can '" + permBit.getValue() + "': " + permBit.getName());

			assertTrue(authHolder.can( permBit.getValue(), WORK ));
			assertTrue(authHolder.can( new String[] {permBit.getName()}, WORK ));
			assertTrue(authHolder.can( permBit.getName(), WORK ));

			assertTrue(authHolder.hasPermissions( WORK, permBit.getValue() ));
			assertTrue(authHolder.hasPermissions( WORK, new String[] {permBit.getName()} ));
			assertTrue(authHolder.hasPermission(  WORK, permBit.getName() ));
		}

		// test that when we set a permission of 0 we remove the permission record
		authHolder.grantPermissions(workContext, 0);
		assertNull(authHolder.permissionsGranted.get(new PermissionGrantedKey(workContext, false)));

		try { 
			log.debug("Test null permissionContext");
			authHolder.grantPermissions(null, 0);
			fail("null permissionContexts are not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}

		try { 
			log.debug("Test negative permission value Failure");
			authHolder.grantPermissions(workContext, -1);
			fail("negative permissions are not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}

		try { 
			log.debug("Test maximum permission Failure");
			authHolder.grantPermissions(workContext, workContext.getMaxValue() + 1);
			fail("permissions outside the range are not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}
	}

	/** 
	 * tests a AuthorizationHolderBase that does not have Roles attached to it (and
	 * hence no inherited Permissions
	 */
	public void testHasPermissionsWithoutRoles()
	{
		authHolder.grantPermissions(workContext, workContext.getMaxValue());

		assertTrue(authHolder.can( workContext.getMaxValue(), WORK));
		assertTrue(authHolder.hasPermissions(WORK, workContext.getMaxValue()));

		for (PermissionBit permBit : workContext.getPermissionBits())
		{
			log.debug("testing hasPermissions '" + permBit.getName() + "': " + permBit.getValue());
			log.debug("testing can '" + permBit.getValue() + "': " + permBit.getName());
			assertTrue(authHolder.can( permBit.getValue(), WORK ));
			assertTrue(authHolder.hasPermissions( WORK, new String[] {permBit.getName()} ));
		}

		String[] READ          = {mock.PERM_READ};
		String[] READ_UPDATE   = {mock.PERM_READ, mock.PERM_UPDATE};
		String[] READ_DISABLE  = {mock.PERM_READ, mock.PERM_DISABLE};

		authHolder.grantPermissions(workContext, workContext.getValue(READ));

		assertTrue(authHolder.can(  workContext.getValue(READ), WORK ));
		assertTrue(authHolder.can(  READ, WORK ));

		assertFalse(authHolder.can( workContext.getValue(READ_UPDATE), WORK ));
		assertFalse(authHolder.can( READ_UPDATE, WORK ));


		assertTrue(authHolder.hasPermissions(  WORK, workContext.getValue(READ) ));
		assertTrue(authHolder.hasPermissions(  WORK, READ ));

		assertFalse(authHolder.hasPermissions( WORK, workContext.getValue(READ_UPDATE) ));
		assertFalse(authHolder.hasPermissions( WORK, READ_UPDATE ));

		for (int i=0; i < READ.length ; i++) {
			assertEquals( READ[i], authHolder.getPermissions(WORK)[i] );
		}

		authHolder.grantPermissions(workContext, workContext.getValue(READ_UPDATE));

		assertTrue(authHolder.can(  workContext.getValue(READ), WORK ));
		assertTrue(authHolder.can(  READ, WORK ));

		assertTrue(authHolder.hasPermissions(  WORK, workContext.getValue(READ) ));
		assertTrue(authHolder.hasPermissions(  WORK, READ ));

		for (int i=0; i < READ.length ; i++) {
			assertEquals( READ_UPDATE[i], authHolder.getPermissions(WORK)[i] );
		}

		assertTrue(authHolder.can(  workContext.getValue(READ_UPDATE), WORK ));
		assertTrue(authHolder.can(  READ_UPDATE, WORK ));

		assertFalse(authHolder.can( workContext.getValue(READ_DISABLE), WORK ));
		assertFalse(authHolder.can( READ_DISABLE, WORK ));

		assertTrue(authHolder.hasPermissions(  WORK, workContext.getValue(READ_UPDATE) ));
		assertTrue(authHolder.hasPermissions(  WORK, READ_UPDATE ));

		assertFalse(authHolder.hasPermissions( WORK, workContext.getValue(READ_DISABLE) ));
		assertFalse(authHolder.hasPermissions( WORK, READ_DISABLE ));

		try { 
			log.debug("Test querying for 0 perms failure");
			authHolder.can(0, WORK);
			fail("querying for 0 perms not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}

		try { 
			log.debug("Test querying for negative perms failure");
			authHolder.can(-1, WORK);
			fail("querying for neg perms not allowed");
		}
		catch (IllegalArgumentException e) {
			// all is well
		}
	}

} // end class AuthorizationHolderTest
