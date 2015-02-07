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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.extensions.TestSetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ***************************************************************************************************
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.2.09 - November 2008
 ***************************************************************************************************
 */
public class PhoneUtilsTest extends TestCase
{
 Logger log = LoggerFactory.getLogger(this.getClass());

	public void testMakeNumeric()
	{
		assertEquals("12345678901", PhoneUtils.makeNumeric("+1 (234) 567-8901"));
		assertEquals("12345678901", PhoneUtils.makeNumeric("1-234-567-8901"));
		assertEquals("12345678901", PhoneUtils.makeNumeric("  !@#$%^&*()1   234 5678901  "));
		assertEquals("12345678901", PhoneUtils.makeNumeric("12345678901"));
	}

} // end class PhoneUtilsTest
