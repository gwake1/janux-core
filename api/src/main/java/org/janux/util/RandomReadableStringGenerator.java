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

package org.janux.util;

/**
 ***************************************************************************************************
 * Interface for a class that generates random string codes of varying length; such generators can
 * be used to generate passwords, alpha-numeric identifiers for orders, or mock data; this interface
 * heavily inspired by 
 * <a href="http://ostermiller.org/utils/RandPass.html">Stephen Ostermiller's RandPass utility class</a>
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 2005-12-19
 ***************************************************************************************************
 */
public interface RandomReadableStringGenerator extends RandomStringGenerator
{
	/** returns a random string with the specified length */
	String getString(int length);

	/** get the alphabet (universe of characters) used by this random string generator */
	char[] getAlphabet();

	/** get the alphabet (universe of characters) used by this random password generator */
	void setAlphabet(char[] alphabet);

} // end class
