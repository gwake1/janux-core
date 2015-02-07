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

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ***************************************************************************************************
 * Tests the SortOrderComparator
 *
 * @author  <a href="mailto:philippe.paravicini@eCommerceStudio.com">Philippe Paravicini</a>
 *
 * @see SortOrderComparator
 * @see Sorteable
 ***************************************************************************************************
 */
public class SortOrderComparatorTest extends TestCase
{
 Logger log = LoggerFactory.getLogger(this.getClass());

	/** executed prior to each test */
	protected void setUp() { }

	/** executed after each test */
	protected void tearDown() { }

	public void testSortOrderComparator()
	{
		SorteableTester s1 = new SorteableTester(new Integer(7));
		SorteableTester s2 = new SorteableTester(new Integer(-5));
		SorteableTester s3 = new SorteableTester(null);
		SorteableTester s4 = new SorteableTester(null);

		SortOrderComparator c = new SortOrderComparator();

		log.debug("s1:s3 " + c.compare(s1,s3));
		log.debug("s1: " + s1.getSortOrder());
		log.debug("s3: " + s3.getSortOrder());
		log.debug("min: " + Integer.MIN_VALUE);
		log.debug("s1-min " + (s1.getSortOrder() - (int)(Integer.MIN_VALUE)));

		assertTrue("s1:s1", c.compare(s1,s1) == 0);
		assertTrue("s2:s2", c.compare(s2,s2) == 0);
		assertTrue("s3:s3", c.compare(s3,s3) == 0);

		assertTrue("s1:s2", c.compare(s1,s2) > 0);
		assertTrue("s2:s1", c.compare(s2,s1) < 0);

		assertTrue("s1:s3", c.compare(s1,s3) > 0);
		assertTrue("s3:s1", c.compare(s3,s1) < 0);

		assertTrue("s2:s3", c.compare(s2,s3) > 0);
		assertTrue("s3:s2", c.compare(s3,s2) < 0);

		assertTrue("s3:s4", c.compare(s3,s4) == 0);
		assertTrue("s4:s3", c.compare(s4,s3) == 0);
	}

} // end class SortOrderComparatorTest

