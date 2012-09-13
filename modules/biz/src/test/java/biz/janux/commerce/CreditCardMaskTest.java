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

package biz.janux.commerce;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.extensions.TestSetup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 ***************************************************************************************************
 *
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since
 ***************************************************************************************************
 */
public class CreditCardMaskTest extends TestCase
{

	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		return new TestSuite(CreditCardMaskTest.class);
	}

	public void testMaskNumber()
	{
		CreditCardMask mask = new CreditCardMask();
		System.out.println("maskNumber(1234567890654321) is: " + mask.maskNumber("1234567890654321"));

		assertEquals("1***********4321", mask.maskNumber("1234567890654321"));
		assertEquals("1234", mask.maskNumber("1234") );

		mask.setNumFrontDigits(2);
		mask.setNumBackDigits(3);
		mask.setMaskingChar('x');
		assertEquals("12xxxxxxxxxx321", mask.maskNumber("123456789054321"));

		try
		{ 
			mask.setNumFrontDigits(-1);
			fail("setNumFrontDigits(-1) is illegal");
		}
		catch (Exception e)
		{
			// expected
		}

		try
		{ 
			mask.setNumBackDigits(-1);
			fail("setNumBackDigits(-1) is illegal");
		}
		catch (Exception e)
		{
			// expected
		}
	}

} // end class CreditCardMaskTest

