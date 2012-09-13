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

package biz.janux.payment.gateway;

import org.springframework.context.ApplicationEvent;


/**
 ***************************************************************************************************
 * In order for the payment-gateway to become operational, it must be initialized with the Password
 * Encryption Key that can be used to decrypt the credentials to the encrypted back-end stoarge
 * engine; this event is triggered when the Password Encryption Key is successfully provided
 * (presumably via the web ui), and contains the key; the key should not be logged, nor should it
 * be kept in memory after the necessary credentials are decrypted, since it is no longer needed at
 * that point.
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4.0
 ***************************************************************************************************
 */
public class KeyInitEvent extends ApplicationEvent
{
	/** plain vanilla constructor that calls the parent's constructor */
	public KeyInitEvent(Object aKey) {
		super(aKey);
	}

	/** the Password Encryption Key necessary to initialize the payment gateway */
	public String getKey() {
		return (String)this.getSource();
	}

} // end class
