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

import java.util.Map;

/**
 ***************************************************************************************************
 * 
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since
 ***************************************************************************************************
 */
public interface CredentialHolder<T>
{
	/** 
	 * Returns a map of Credential objects that are necessary in order to operate this service, for
	 * for example, the credentials necessary to connect to an encryption back-end system.
	 */
	public Map<String, T> getCredentials();
}


