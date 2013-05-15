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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class PropertyMetadataImpl implements PropertyMetadata
{
	private static final long serialVersionUID = -7194852247759431086L;
	/** the name (key) of the property */
	private final String name;
	/** the display name of the property */
	private final String label;
	/** identifies the datatype for the property */
	private DataType dataType;
    /** property validator */
	private PropertyValidator validator;
	
	public PropertyMetadataImpl(final String aName, final String aLabel, final DataType aDataType)
	{
	    name = aName;
	    label = aLabel;
	    dataType = aDataType;
	}
	
	
	public DataType getDataType()
	{
		return dataType;
	}

	public String getName()
	{
		return name;
	}

	public String getLabel()
	{
		return label;
	}

	
	public PropertyValidator getValidator()
	{
		return validator;
	}


	public void setValidator(PropertyValidator validator)
	{
		this.validator = validator;
	}


	/**  Two metadata objects are equal if the names are equal */
	public boolean equals(final Object aObject)
	{
		if (this == aObject) 
		{
			return true;
		}
		
		if (!(aObject instanceof PropertyMetadataImpl)) 
		{
			return false;
		}
		
		final PropertyMetadataImpl thatObject = (PropertyMetadataImpl)aObject; 

		return new EqualsBuilder()
			.append(this.getName(), thatObject.getName())
			.isEquals();
	}

	public int hashCode() 
	{
		return new HashCodeBuilder()
		.append(this.getName())
		.toHashCode();
	}

	@SuppressWarnings("unchecked")
	public Object clone() 
	{
	    try 
	    {
	        PropertyMetadataImpl result = (PropertyMetadataImpl) super.clone();
	    
	        if (this.validator instanceof PropertyValidator)
	        {
	            result.validator = (PropertyValidator) this.validator.clone();
	        }
	        
	        return result;
	    } 
	    catch (CloneNotSupportedException e) 
	    {
	        return null;
	    }
	}
	
}
