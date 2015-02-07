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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 ***************************************************************************************************
 * Convenience class to store formatting or validation methods that are commonly used when handling
 * phone numbers
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.2.09
 ***************************************************************************************************
 */
public class PhoneUtils
{
 Logger log = LoggerFactory.getLogger(this.getClass());

	/** returns the pre-defined non-digit character class: "\D" which is equivalent to [^0-9] */
	public final static String REGEXP_MATCH_NON_NUMERIC = "\\D";

	/** 
	 * accepts a phone string with alphanumeric characters, and strips out all spaces and non-numeric
	 * characters
	 */
	public static String makeNumeric(String aPhoneString)
	{
		if (aPhoneString != null) 
		{
			return aPhoneString.replaceAll(REGEXP_MATCH_NON_NUMERIC,"");
		}
		else return null;
	}

} // end class
