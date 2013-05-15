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

package biz.janux.people.net;

import java.net.URI;

import biz.janux.people.ContactMethod;

/**
 * sub-interface of ContactMethod that generically represents an electronic
 * address or Uniform Resource Identifier; this interface provides a
 * string 'address' that can be used to persist a string representation of the
 * URI, and a getUri method that parses the string and returns a java.net.URI
 * instance
 */
public interface Uri extends ContactMethod
{
	/** the string representation of this Uniform Resource Identifier */
	String getAddress();
	void setAddress(String s);

	/** the java language representation of this Uniform Resource Identifier */
	URI getUri() throws java.net.URISyntaxException;
}
