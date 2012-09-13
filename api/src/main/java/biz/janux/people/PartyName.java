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

import java.io.Serializable;

/**
 * Simple bean to store a short and long name for a Person or Organization
 */
public interface PartyName extends Cloneable, Serializable
{		
	/** a short name to refer to a Person or Organization */
	String getShort();
	void setShort(String s);

	/** a long (or legal) name to refer to a Person or Organization */
	String getLong();
	void setLong(String s);
	
	Object clone();
}
