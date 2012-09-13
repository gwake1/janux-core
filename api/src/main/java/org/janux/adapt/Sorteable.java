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

package org.janux.adapt;

/**
 ***************************************************************************************************
 * Simple interface for entities that may be assigned a sort order; this field may be left null, in
 * which case the comparator is left to decide whether this is permissible, whether entities with a
 * null sort order should come first/last, and how entities with null sort order should be sorted
 * among themselves
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.1 $ - $Date: 2005-12-20 23:49:25 $
 ***************************************************************************************************
 */
public interface Sorteable
{
	Integer getSortOrder();
	void setSortOrder(Integer i);
}
