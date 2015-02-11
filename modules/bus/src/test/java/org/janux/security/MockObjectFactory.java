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

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.janux.security.metadata.*;

/**
 ***************************************************************************************************
 * Singleton class used to create sample AuthorizationContext and Roles for testing purposes
 *
 * @author  <a href="mailto:philippe.paravicini@eCommerceStudio.com">Philippe Paravicini</a>
 * @version $Revision: 1.1 $ - $Date: 2006-12-07 01:55:18 $
 *
 * @see
 ***************************************************************************************************
 */
public class MockObjectFactory
{
 Logger log = LoggerFactory.getLogger(this.getClass());
	//static Logger log = LoggerFactory.getLogger(FooSetup.class);
	
	static final String ACCOUNT      = "ACCOUNT";

	static final String ROLE_GODDESS         = "GODDESS";
	static final String ROLE_WORK_SUPERVISOR = "WORK_SUPERVISOR";
	static final String ROLE_MANAGER         = "MANAGER";
	static final String ROLE_SUPER_HUMAN     = "SUPER_HUMAN";
	static final String ROLE_ENGINEER        = "ENGINEER";
	static final String ROLE_HUMAN           = "HUMAN";
	static final String ROLE_WORK_MANAGER    = "WORK_MANAGER";
	static final String ROLE_HOLIDAY_MANAGER = "HOLIDAY_MANAGER";
	static final String ROLE_HOLIDAY_TAKER   = "HOLIDAY_TAKER";
	static final String ROLE_ACCOUNT_ADMIN   = "ACCOUNT_ADMIN";
	static final String ROLE_ROLE_ADMIN      = "ROLE_ADMIN";

	static final String CTX_ACCOUNT = "CTX_ACCOUNT";
	static final String CTX_ROLE    = "CTX_ROLE";
	static final String CTX_HOLIDAY = "CTX_HOLIDAY";
	static final String CTX_WORK    = "CTX_WORK";

	static final String PERM_READ    = "READ";
	static final String PERM_UPDATE  = "UPDATE";
	static final String PERM_CREATE  = "CREATE";
	static final String PERM_DISABLE = "DISABLE";
	static final String PERM_PURGE   = "PURGE";
	
	static final String PERM_DECLARE = "DECLARE";
	static final String PERM_APPROVE = "APPROVE";
	static final String PERM_TAKE    = "TAKE";
	static final String PERM_DO      = "DO";
	static final String PERM_ASSIGN  = "ASSIGN";
	static final String PERM_SKIP    = "SKIP";

	static final String[] STANDARD_PERMS = {PERM_READ, PERM_UPDATE, PERM_CREATE, PERM_DISABLE, PERM_PURGE};

	/*
	static MockObjectFactory singleton;

	public MockObjectFactory() {
		if (singleton == null)
			singleton = new MockObjectFactory();
	}
	*/

	/** 
	 * returns a AuthorizationContext with the name specified, and with the
	 * standard permissions 'READ', 'UPDATE', 'CREATE', 'DISABLE', 'PURGE'
	 */
	public static AuthorizationContext getAuthorizationContext(String name) 
	{
		return getAuthorizationContext(name, STANDARD_PERMS);
	}

	/** 
	 * returns a AuthorizationContext with the name specified, and the Permissions
	 * provided in the string, where the bit position of the permissions
	 * corresponds to the position of the permission name in the string array
	 */
	public static AuthorizationContext getAuthorizationContext(String name, String[] perms) 
	{
		AuthorizationContext authContext = new AuthorizationContextImpl(name);

		for (int i=0; i < perms.length ; i++)
			authContext.addPermissionBit(new PermissionBitImpl(perms[i]));

		return authContext;
	}

	/** 
	 * Returns a simple role named 'ROLE_ADMIN' with READ, UPDATE, CREATE, DISABLE permissions on
	 * ROLE entities
	 */
	public static Role getSimpleRole_RoleAdmin() 
	{
		AuthorizationContext ctx_role = getAuthorizationContext(CTX_ROLE, STANDARD_PERMS);

		Role role = new RoleImpl();
		role.setName(ROLE_ROLE_ADMIN);

		//String[] grant_perms = {PERM_READ, PERM_UPDATE, PERM_CREATE, PERM_DISABLE};
	
		//role.grantPermissions( ctx_role, ctx_role.getValue(grant_perms));
		role.grantPermissions( ctx_role, ctx_role.getValue(new String[]{PERM_READ, PERM_UPDATE, PERM_CREATE, PERM_DISABLE}) );

		return role;
	}

	/** 
	 * Returns a complex role named 'ACCOUNT_ADMIN' with READ, UPDATE, CREATE, DISABLE permissions on
	 * ACCOUNT entities, and also aggregates the role 'ROLE_ADMIN'
	 */
	public static Role getComplexRole_AccountAdmin()
	{
		AuthorizationContext ctx_account = getAuthorizationContext(CTX_ACCOUNT, STANDARD_PERMS);

		Role role = new RoleImpl();
		role.setName(ROLE_ACCOUNT_ADMIN);

		role.grantPermissions(ctx_account, ctx_account.getValue(new String[]{PERM_READ, PERM_UPDATE, PERM_CREATE, PERM_DISABLE}));
		role.getRoles().add(getSimpleRole_RoleAdmin());
		

		return role;
	}


} // end class MockObjectFactory

