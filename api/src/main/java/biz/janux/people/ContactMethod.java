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

package biz.janux.people;

import java.io.Serializable;
import java.util.Map;

import org.janux.bus.persistence.Persistent;

/** 
 * marker interface used to signify that a sub-interface or implementing class
 * represents a means of contacting a Party; examples of ContactMethod
 * sub-interfaces are PostalAddress, PhoneNumber, EmailAddress and URL
 */
public interface ContactMethod extends Persistent, Serializable, Cloneable 
{ 
	public Object clone();
}
