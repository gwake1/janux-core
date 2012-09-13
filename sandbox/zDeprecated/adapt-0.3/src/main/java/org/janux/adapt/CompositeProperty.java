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

import java.util.Map;

public interface CompositeProperty extends Property
{
	/**
	 * Returns the property value
	 */
	void setValue(final String aName, final Object aValue);
	
	/**
	 * Returns the property value
	 */
	void setValidValue(final String aName, final Object aValue) throws PropertyValidationException;
	
	void setMetadata(final String aName, final PropertyMetadata.DataType aDataType);
	
	void setMetadata(final String aName, final String aLabel, final PropertyMetadata.DataType aDataType);
	
	void setMetadata(final String aName, final String aLabel, final PropertyMetadata.DataType aDataType, final PropertyValidator aValidator);
	
	/** 
	 * updates or sets the value for the given property
	 *
	 * @throws IllegalArgumentException 
	 *  if a property with the name provided has not been defined, or if the runtime class of the
	 *  object passed does not match the type defined in the metadata
	 */
	// void setChildPropertyMetadata(final PropertyMetadata aPropertyMetadata);

	void clearProperties();

	void add(final Property aProperty);
	
	void remove(final String aName);
	
	int getCount();
	
	/** 
	 * This method expects key/value mappings of String/PrimitiveObject; read
	 * below for more.
	 * 
	 * <ul>
	 * 	<li>
	 * 		the values are java primitive wrapper classes such as String, Date,
	 * 		Boolean, Integer, Long and Double
	 * 	</li><li>
	 * 		<p>
	 * 		the keys of the map are Strings describing some property/attribute/metric
	 * 		of the {@link Extensible} entity that is being extended 
	 * 		</p>
	 * 	</li>
	 * </ul>
	 *
	 */
	// void setProperties(final Map<String, ?> properties);

	
	/**
	 * Returns the property value
	 */
	Property getProperty(final String aName);
	
	/**
	 * Returns the property value
	 */
	Property getProperty(final String aName, final int aRecursiveDepth);
	
	/**
	 * Returns the property value
	 */
	PrimitiveProperty getPrimitiveProperty(final String aName);
	
	/**
	 * Returns the property value
	 */
	PrimitiveProperty getPrimitiveProperty(final String aName, final int aRecursiveDepth);
	
	/**
	 * Returns the property value
	 */
	CompositeProperty getCompositeProperty(final String aName);
	
	/**
	 * Returns the property value
	 */
	CompositeProperty getCompositeProperty(final String aName, final int aRecursiveDepth);
	
	
	Map<String, Property> getProperties();
	
	CompositeProperty createCompositeProperty(final String aName);

	CompositeProperty createCompositeProperty(final String aName, final String aLabel);
}
