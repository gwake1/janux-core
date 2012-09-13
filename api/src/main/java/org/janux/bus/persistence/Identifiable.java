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

package org.janux.bus.persistence;

import java.io.Serializable;

/**
 ***************************************************************************************************
 * Defines a random alphanumeric string that uniquely identifies the objects.
 * This is the code that external clients must use to reference the objects.
 * 	
 * @author  <a href="mailto:albertobuffagni@gmail.com">Alberto Buffagni</a>
 * @version $Revision:  $ - $Date: 2011-01-12 00:00:00 $
 ***************************************************************************************************
 */
public interface Identifiable extends Serializable {

	/**       
	 * A random alphanumeric string that uniquely identifies the objects.
	 */
	public String getUuid();
	public void setUuid(String uuid);
		
}
