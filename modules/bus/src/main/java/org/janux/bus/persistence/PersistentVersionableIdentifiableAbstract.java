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

/**
 ***************************************************************************************************
 * Utility class that provides an Integer identity field, date created/updated fields and a String random
 * 	
 * @author  <a href="mailto:albertobuffagni@gmail.com">Alberto Buffagni</a>
 * @since 0.4
 ***************************************************************************************************
 */
public abstract class PersistentVersionableIdentifiableAbstract extends PersistentVersionableAbstract 
	implements Identifiable
{
	private String uuid;

	public String getUuid() {
		return this.uuid;
	}
	
	public void setUuid(String aUuid) 
	{
		if (aUuid == null || "".equals(aUuid)) {
			throw new IllegalArgumentException("Cannot set a UUID to a null or empty string");
		}

		if (this.uuid != null && !this.uuid.equals(aUuid)) {
			throw new UnsupportedOperationException("A UUID is immutable and cannot be changed");
		}

		this.uuid = aUuid;
	}	
	
} 
