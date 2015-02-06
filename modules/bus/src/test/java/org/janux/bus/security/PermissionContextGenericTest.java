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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.extensions.TestSetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class PermissionContextGenericTest extends TestCase
{
 Logger log = LoggerFactory.getLogger(this.getClass());

	PermissionContext permContext;

	final static String CTX_ACCOUNT = "ACCOUNT";

	/** executed prior to each test */
	protected void setUp() { 
		permContext = MockObjectFactory.getPermissionContext(CTX_ACCOUNT);
	}


	public void testPermissionContext()
	{
		String[] perms = MockObjectFactory.STANDARD_PERMS;

		assertEquals(CTX_ACCOUNT, permContext.getName());
		assertEquals(perms.length, permContext.getPermissionBits().size());

		for (int i=0; i < perms.length; i++)
		{
			PermissionBit permBit = permContext.getPermissionBit(perms[i]);
			assertEquals(perms[i], permBit.getName());
			assertEquals(i, permBit.getPosition());
		} // end for

		assertEquals( 1, permContext.getPermissionBit(perms[0]).getValue());
		assertEquals( 2, permContext.getPermissionBit(perms[1]).getValue());
		assertEquals( 4, permContext.getPermissionBit(perms[2]).getValue());
		assertEquals( 8, permContext.getPermissionBit(perms[3]).getValue());
		assertEquals(16, permContext.getPermissionBit(perms[4]).getValue());
	}

	public void testPermissionBit()
	{
		String PERM_NAME = "DESTROY";
		PermissionBit permBit = new PermissionBitImpl(PERM_NAME);

		assertEquals( PERM_NAME, permBit.getName());
		assertEquals( -1, permBit.getPosition());
		 
		// try some things that should not be tried
		
		try
		{ 
			permBit = new PermissionBitImpl(null);
			fail("Attempting to create a PermissionBit with a null name should have failed !");
		}
		catch (Exception e)
		{
			// we're good
		}

		String ALL_WHITE_SPACE = "  	";

		try
		{ 
			permBit = new PermissionBitImpl(ALL_WHITE_SPACE);
			fail("Attempting to create a PermissionBit with a name with all white space should have failed !");
		}
		catch (Exception e)
		{
			// we're good
		}

	}


	public void testAddPermissionBit() 
	{
		// add a new PermissionBit to a PermissionContext
		String PERM_ARCHIVE = "ARCHIVE";
		permContext.addPermissionBit(new PermissionBitImpl(PERM_ARCHIVE));

		// verify the PermissionContext after the addition
		int NUM_BITS = MockObjectFactory.STANDARD_PERMS.length + 1;
		PermissionBit permBit = permContext.getPermissionBit(PERM_ARCHIVE);

		assertEquals(NUM_BITS, permContext.getPermissionBits().size());
		assertNotNull(permBit);
		assertEquals(PERM_ARCHIVE, permBit.getName());
		assertEquals(NUM_BITS - 1, permBit.getPosition());
		assertEquals(permContext, permBit.getPermissionContext());

		// attempt to add a PermissionBit with a duplicate name
		try
		{ 
			permContext.addPermissionBit(new PermissionBitImpl(PERM_ARCHIVE));
			fail("Attempting to add a PermissionBit under a name that already exists should have failed !");
		}
		catch (Exception e) {
			// all is well !
		}

		// re-verify the PermissionContext after the failed duplicate insert
		permBit = permContext.getPermissionBit(PERM_ARCHIVE);

		assertEquals(NUM_BITS, permContext.getPermissionBits().size());
		assertNotNull(permBit);
		assertEquals(PERM_ARCHIVE, permBit.getName());
		assertEquals(NUM_BITS - 1, permBit.getPosition());
		assertEquals(permContext, permBit.getPermissionContext());
	}

} // end class PermissionContextTest

