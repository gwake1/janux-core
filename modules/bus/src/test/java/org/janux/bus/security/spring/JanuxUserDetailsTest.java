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

package org.janux.bus.security.spring;

import org.apache.commons.beanutils.BeanUtils;

import org.janux.bus.security.*;

import java.util.GregorianCalendar;
import org.springframework.security.core.userdetails.UserDetails;

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
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since
 ***************************************************************************************************
 */
public class JanuxUserDetailsTest extends TestCase
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	//static Logger log = LoggerFactory.getLogger(FooSetup.class);
	
	private AccountImpl account;

	public void setUp() {
		account = new AccountImpl();
		account.setName("AccountName");
		account.setPassword("Die5uzir");

		GregorianCalendar cal = new GregorianCalendar(2012,3,21);
		account.setExpiration(cal.getTime());

		cal = new GregorianCalendar(2011,12,24);
		account.setPasswordExpiration(cal.getTime());

		account.setEnabled(true);
		account.setAccountNonLocked(true);

		account.getRoles().add(MockObjectFactory.getComplexRole_AccountAdmin());
	}

	public void testCloning() 
	{
		log.debug("account to be cloned: {}", account);
		UserDetails userDetails = new JanuxUserDetails(account);

		assertEquals(account.getUsername(), userDetails.getUsername());
		assertEquals(account.getPassword(), userDetails.getPassword());
		assertEquals(account.isEnabled(),   userDetails.isEnabled());
		assertEquals(account.isAccountNonExpired(),     userDetails.isAccountNonExpired());
		assertEquals(account.isAccountNonLocked(),      userDetails.isAccountNonLocked());
		assertEquals(account.isCredentialsNonExpired(), userDetails.isCredentialsNonExpired());
		log.debug("UserDetails is : '{}'",userDetails);
	}

	public void testCloningWithNulls() 
	{
		AccountImpl emptyAccount = new AccountImpl();
		log.debug("account to be cloned: {}", emptyAccount);
		UserDetails userDetails = new JanuxUserDetails(emptyAccount);

		assertEquals(emptyAccount.getUsername(), userDetails.getUsername());
		assertEquals(emptyAccount.getPassword(), userDetails.getPassword());
		assertEquals(emptyAccount.isEnabled(),   userDetails.isEnabled());
		assertEquals(emptyAccount.isAccountNonExpired(),     userDetails.isAccountNonExpired());
		assertEquals(emptyAccount.isAccountNonLocked(),      userDetails.isAccountNonLocked());
		assertEquals(emptyAccount.isCredentialsNonExpired(), userDetails.isCredentialsNonExpired());

		log.debug("UserDetails is : '{}'",userDetails);
	}

}

