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

public class PropertyValidationException extends Exception
{
	private static final long serialVersionUID = -505673090589753944L;
	private Property property;
	private Object value;

	public PropertyValidationException(final String aMessage)
	{
	    super(aMessage);
    }
	
	public PropertyValidationException(final String aMessage, final Object aValue)
	{
	    super(aMessage);
		
	    value = aValue;
	}
	
	public PropertyValidationException(final String aMessage, final Object aValue, final Property aProperty)
	{
	    super(aMessage);
		
	    property = aProperty;
	    value = aValue;
	}
	
	public Property getProperty()
	{
		return property;
	}

	public Object getValue()
	{
		return value;
	}

	public void setProperty(Property property)
	{
		this.property = property;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}
	
}
