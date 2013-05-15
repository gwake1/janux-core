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

import java.util.Comparator;

/** 
 * sorts classes that implement the Sorteable interface, that is, those which
 * have a getSortOrder method; sorteable objects with a null sortOrder field
 * are sorted last
 *
 * Note that this comparator may impose an ordering that is not consistent with
 * .equals() as in most cases this comparator is used to order objects in an
 * enumeration or visual display, and may not be related to the business
 * equality of the class
 *
 * @see Comparator
 */
public class SortOrderComparator implements Comparator<Sorteable>
{
	/** 
	 * Generally returns s1.getSortOrder() - s2.getSortOrder; the values are
	 * converted to long at the time of calculation, and the result is
	 * constrained to be between Integer.MIN_VALUE and Integer.MAX_VALUE
	 */
	public int compare(Sorteable s1, Sorteable s2)
	{
		long so1 = (long) ( (s1.getSortOrder() != null) ? s1.getSortOrder() : Integer.MIN_VALUE );
		long so2 = (long) ( (s2.getSortOrder() != null) ? s2.getSortOrder() : Integer.MIN_VALUE );

		if (so1 - so2 > 0)
			return (int) Math.min(so1-so2, Integer.MAX_VALUE);
		else
			return (int) Math.max(so1-so2, Integer.MIN_VALUE);
	}
}
