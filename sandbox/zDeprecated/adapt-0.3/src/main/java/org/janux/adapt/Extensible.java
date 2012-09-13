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
 * Defines methods for classes that can be extended via 'soft-coded' properties without changing the
 * class declaration, and, in particular, without the necessity to declare getter/setters for these
 * properties.  
 * <p>
 * The general concept at this time borrows from from loosely-typed languages such as
 * javascript, where a class and its properties can be represented as a map (associative array), and
 * accessed via the keys of such map.
 * </p>
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.5 $ - $Date: 2006-10-20 16:28:18 $
 * @since 2005.11.14
 ***************************************************************************************************
 */

public interface Extensible
{
	public CompositeProperty getPropertyMap();
}
