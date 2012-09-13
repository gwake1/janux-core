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

package org.janux.cas;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.janux.bus.security.AccountService;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.AuthenticationHandler;
import org.jasig.cas.authentication.handler.UnknownUsernameAuthenticationException;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;


/**
 * CAS AuthenticationHandler that allows to authenticate against the janux {@link AccountService}
 *
 * @author  <a href="mailto:moradaniel@janux.org">Daniel Mora</a>
 */
public class AccountServiceAuthenticationHandler implements AuthenticationHandler {
	
	/** Class logger */
	private static final Log log = LogFactory.getLog(AccountServiceAuthenticationHandler.class);
	
	
	private UserDetailsService userDetailsService;
	

	public boolean authenticate(Credentials credentials) throws AuthenticationException {
		
		UsernamePasswordCredentials usernameAndPassword = (UsernamePasswordCredentials) credentials;
		
		UserDetails userDetails = getUserDetailsService().loadUserByUsername(usernameAndPassword.getUsername());
		
		if(userDetails!=null){
			if(userDetails.getPassword().equals(usernameAndPassword.getPassword())){
				return true;
			}
		}else{
			log.warn("Couldn't find any user with username [" + usernameAndPassword.getUsername() + "]"); 
			throw new UnknownUsernameAuthenticationException("error.authentication.credentials.bad");

		}
		
		return false;
	}

	public boolean supports(Credentials credentials) {
		if (credentials instanceof UsernamePasswordCredentials) {
			return true;
		} else {
			return false;
		}
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}
