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

import java.util.SortedSet;

import org.janux.security.Account;
import org.janux.security.AccountFacet;
import org.janux.bus.persistence.EntityNotFoundException;
import org.janux.bus.persistence.GenericDaoReadOnlyWithFacets;
import org.janux.bus.persistence.GenericDaoWrite;
import org.janux.bus.search.SearchCriteria;
import org.jasypt.digest.StringDigester;

/**
 * Used to create, save, retrieve, update and delete Account objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 */

public interface AccountDaoGeneric<T extends Account> 
	extends GenericDaoWrite<T, Integer>,
	GenericDaoReadOnlyWithFacets<T, Integer, SearchCriteria, AccountFacet>
{
	/**
	 * Returns an Account by names, or <code>null</code> if the Account is not found.
	 *
	 * @param name the Account name
	 */
	public Account findByName(String name);


	/**
	 * loads an Account object, or throws exception if Account with that name is not found
	 *
	 * @param name a name that uniquely identifies this Account
	 *
	 * @throws EntityNotFoundException if a Account object with that name is not found
	 */
	public Account loadByName(String name) throws EntityNotFoundException;

	
	/** Loads all Accounts defined in the system */
	public SortedSet<Account> loadAll(boolean initializeAccounts);

	
	/** returns a new Account instance */
	public Account newAccount();
	
	public StringDigester getPasswordEncryptor();

	public void setPasswordEncryptor(StringDigester passwordEncryptor);
}
