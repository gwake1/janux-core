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

package org.janux.security.persist;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.janux.bus.persistence.EntityNotFoundException;
import org.janux.bus.persistence.TransactionalTestAbstract;
import org.janux.bus.test.TransactionalBusTestAbstractGeneric;

import org.janux.security.*;
import org.janux.security.metadata.*;


/**
 * @author   <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version  $Revision: 1.2 $ - $Date: 2006-08-29 21:02:28 $
 *
 * @see TransactionalTestAbstract
 */
public class AuthorizationContextDaoGenericTest extends TransactionalBusTestAbstractGeneric
{
 Logger log = LoggerFactory.getLogger(this.getClass());

	protected AuthorizationContextDaoGeneric authContextDaoGeneric;

	private static final int NUM_PERM_CTX = 4;
	private static final String ACCOUNT = "CTX_ACCOUNT";
	private static final String ROLE    = "CTX_ROLE";
	private static final String HOLIDAY = "CTX_HOLIDAY";
	private static final String WORK    = "CTX_WORK";

	private static final int NUM_HOLIDAY_PERM_BITS = 3;
	private static final String HOL_DECLARE = "DECLARE";
	private static final String HOL_APPROVE = "APPROVE";
	private static final String HOL_TAKE    = "TAKE";

	public AuthorizationContextDaoGenericTest() {
		super();
	}

	public AuthorizationContextDaoGenericTest(String name) {
		super(name);
	}

	public void testLoadAll() 
	{
		SortedSet<AuthorizationContext> set = authContextDaoGeneric.loadAll();
		assertNotNull(set);
		assertEquals("perm num", NUM_PERM_CTX, set.size());

		Iterator i = set.iterator();
		AuthorizationContext authContext;

		authContext = (AuthorizationContext)i.next();
		assertEquals(ACCOUNT, authContext.getName());

		authContext = (AuthorizationContext)i.next();
		assertEquals(ROLE, authContext.getName());

		authContext = (AuthorizationContext)i.next();
		assertEquals(HOLIDAY, authContext.getName());

		authContext = (AuthorizationContext)i.next();
		assertEquals(WORK, authContext.getName());
	}


	public void testLoadByName() 
	{
		AuthorizationContext authContext = authContextDaoGeneric.loadByName(HOLIDAY);
		assertContext(authContext);

		try
		{ 
			authContextDaoGeneric.loadByName("BOGUS");
			fail("Loading BOGUS permission context should have thrown EntityNotFoundException");
		}
		catch (EntityNotFoundException e) { 
			// expected behavior
		}
	}

	public void testFindByName() 
	{
		AuthorizationContext authContext = authContextDaoGeneric.findByName(HOLIDAY);
		assertContext(authContext);

		assertNull(authContextDaoGeneric.findByName("BOGUS"));
	}

	private void assertContext(AuthorizationContext authContext)
	{
		assertNotNull(authContext);
		assertEquals(HOLIDAY, authContext.getName());

		List<PermissionBit> bits = authContext.getPermissionBits();
		assertNotNull(bits);
		assertEquals("num " + HOLIDAY + " bits", NUM_HOLIDAY_PERM_BITS, bits.size());

		PermissionBit bit;

		bit = bits.get(0);
		assertEquals(HOL_DECLARE, bit.getName());
		assertEquals(HOL_DECLARE + " sort order", new Integer(1), bit.getSortOrder());
		assertEquals(bit, authContext.getPermissionBit(HOL_DECLARE));

		bit = bits.get(1);
		assertEquals(HOL_APPROVE, bit.getName());
		assertEquals(HOL_APPROVE + " sort order", new Integer(0), bit.getSortOrder());
		assertEquals(bit, authContext.getPermissionBit(HOL_APPROVE));

		bit = bits.get(2);
		assertEquals(HOL_TAKE, bit.getName());
		assertEquals(HOL_TAKE + " sort order", new Integer(2), bit.getSortOrder());
		assertEquals(bit, authContext.getPermissionBit(HOL_TAKE));
	}

} // end class PermissionDaoTest
