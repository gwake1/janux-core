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

import java.io.Serializable;

public interface Property extends Cloneable, Serializable
{
	PropertyMetadata getMetadata();
	
	Object getValue();
	
	Object getValidValue();
	
	void setValue(Object value);
	
	void setValidValue(Object value) throws PropertyValidationException;
	
	void validate() throws PropertyValidationException;
	
	void accept(PropertyVisitor visitor);
	
	Object clone();
}
