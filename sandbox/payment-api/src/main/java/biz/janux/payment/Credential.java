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

package biz.janux.payment;

/**
 ***************************************************************************************************
 * This is a simple username/password string tuple currently used for demonstrating how to unencrypt
 * credentials at initialization time; it may be desirable to eventually integrate and develop this
 * concept as an interface inside the org.janux.security package.
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4.01 - 2012-04-26
 ***************************************************************************************************
 */
public class Credential
{
	private String username;
	private String password;

	/** A username */
  public String getUsername() { return this.username;}
  public void setUsername(String n) { this.username = n; }


	/** A password */
  public String getPassword() { return this.password;}
  public void setPassword(String n) { this.password = n; }

} // end class Credential
