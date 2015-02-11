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

import java.util.SortedSet;

import org.janux.security.metadata.*;
import org.janux.bus.persistence.EntityNotFoundException;

/**
 * Used to create, save, retrieve, update and delete Account objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.1
 */
public interface AccountService
{
	/**
	 * Returns an Account by names, or <code>null</code> if the Account is not found.
	 *
	 * @param name the Account name
	 */
	public Account findAccountByName(String name);


	/**
	 * loads an Account object, or throws exception if Account with that name is not found
	 *
	 * @param name a name that uniquely identifies this Account
	 *
	 * @throws EntityNotFoundException if a Account object with that name is not found
	 */
	public Account loadAccountByName(String name) throws EntityNotFoundException;

	
	/** Loads all Accounts defined in the system */
	public SortedSet<Account> loadAllAccounts(boolean initializeAccounts);


	/** returns a new Account instance */
	public Account newAccount();

	public void saveAccount(final Account account);
	public void saveOrUpdateAccount(final Account account);
	public void deleteAccount(final Account account);


	/**
	 * Returns a Role by names, or <code>null</code> if the Role is not found.
	 *
	 * @param name the Role name
	 */
	public Role findRoleByName(String name);


	/**
	 * loads an Role object, or throws exception if Role with that name is not found
	 *
	 * @param name a name that uniquely identifies this Role
	 *
	 * @throws EntityNotFoundException if a Role object with that name is not found
	 */
	public Role loadRoleByName(String name) throws EntityNotFoundException;

	
	/** Loads all Roles defined in the system */
	public SortedSet<Role> loadAllRoles();


	/** Loads all AuthorizationContexts defined in the system */
	public SortedSet<AuthorizationContext> loadAllAuthorizationContexts();
	
	
	public void setAccountPassword(Account account, String passwordToEncrypt);

	
}
