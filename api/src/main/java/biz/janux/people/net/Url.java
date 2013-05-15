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

import java.net.URL;
import java.net.MalformedURLException;

/**
 * sub-interface of ContactMethod and Uri that generically represents an electronic
 * address or Uniform Resource Locator; this interface provides a string
 * 'address' that can be used to persist a string representation of the URL, and
 * a getUrl method that parses the string and returns a java.net.URL instance
 */
public interface Url extends Uri
{
	/** the java language representation of this Uniform Resource Locator */
	URL getUrl() throws MalformedURLException;
}
