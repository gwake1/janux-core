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

/**
 * Simple bean to store the name of a Person
 */
public interface PersonName extends PartyName
{		
	String getFirst();
	void setFirst(String s);

	String getMiddle();
	void setMiddle(String s);

	String getLast();
	void setLast(String s);

	/** titles that may be used before a name such as: Mr., Ms., Dr., etc...*/
	String getHonorificPrefix();
	void setHonorificPrefix(String s);

	/** titles that are used after a name such as Jr., Sr., M.D., C.P.A., etc... */
	String getHonorificSuffix();
	void setHonorificSuffix(String s);
}
