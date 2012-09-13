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

package org.janux.bus.security;

/** 
 * This is a temporary interface to store account settings that will be deprecated in favor of the
 * more general interfaces in the org.janux.adapt package
 */
public interface AccountSetting
{
	void setTag(String tag);
	String getTag();
	
	void setValue(String value);
	String getValue();
}
