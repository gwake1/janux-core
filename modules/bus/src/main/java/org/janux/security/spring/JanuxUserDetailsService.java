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

package org.janux.security.spring;

import org.janux.security.*;
import org.janux.security.persist.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 ***************************************************************************************************
 * Adapter class that implements a spring security UserDetailsService for authentication; implements
 * the single UserDetailsService method by delegating to 
 * {@link AccountDaoGeneric#findByName(String username)}
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4.0 - 2012-03-28
 ***************************************************************************************************
 */
public class JanuxUserDetailsService implements UserDetailsService
{
	private AccountDaoGeneric<Account> accountDao;

	/** The account dao to which this UserDetailsService delegates */
  public AccountDaoGeneric<Account> getAccountDao() { return accountDao;}
  public void setAccountDao(AccountDaoGeneric<Account> o) { accountDao = o; }

	public UserDetails loadUserByUsername(String username) 
	{
		Account account = this.getAccountDao().findByName(username);
		if (account != null) {
			return new JanuxUserDetails(account);
		}
		else {
			return null;
		}
	}

} // end class JanuxUserDetailsService
