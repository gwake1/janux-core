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

import java.util.HashSet;
import java.util.Set;

public class StringValidator implements PropertyValidator
{
	private Integer minLength;
	private Integer maxLength;
	private Set<String> picklist;
    private boolean picklistOnly;

    
    /**
     * Constructor for defining a valid range of string values
     */
	public StringValidator()
	{
	    picklist = new HashSet<String>();
	}
    
    
    /**
     * Constructor for defining a valid range of string values
     * @param aMinLength the minimum allowed length value
     * @param aMaxLength the maximum allowed length value
     */
	public StringValidator(final Integer aMinLength, final Integer aMaxLength)
	{
		this();
	    setMinLength(aMinLength);
	    setMaxLength(aMaxLength);
	}
	
	
    /**
     * Constructor for defining a valid range of string values
     * @param aPicklist a list of allowed values
     */
	public StringValidator(final Set<String> aPicklist)
	{
		this();
		setPicklist(aPicklist);
		setPicklistOnly(true);
	}
	
    

	public Integer getMaxLength()
	{
		return maxLength;
	}


	public void setMaxLength(Integer maxLength)
	{
		this.maxLength = maxLength;
	}


	public Integer getMinLength()
	{
		return minLength;
	}


	public void setMinLength(Integer minLength)
	{
		this.minLength = minLength;
	}


	public Set<String> getPicklist()
	{
		return (new HashSet<String>(this.picklist));
	}


	public void setPicklist(Set<String> picklist)
	{
		this.picklist.clear();
		this.picklist.addAll(picklist);
	}


	public boolean isPicklistOnly()
	{
		return picklistOnly;
	}


	public void setPicklistOnly(boolean picklistOnly)
	{
		this.picklistOnly = picklistOnly;
	}

	
	public void validate(final Object aValue) throws PropertyValidationException 	
	{
		if (!(aValue instanceof String))
		{
			throw new PropertyValidationException("Value must be a string type", aValue);
		}
		
		final String value = (String )aValue;
		
		// check the min length value
		if (minLength instanceof Integer)
		{
			if (value.length() < minLength.intValue())
			{
				throw new PropertyValidationException("Length of value '" + value + "' is less than the minimum allowed length of " + minLength + " characters", aValue);
			}
		}
		
		// check the max length value
		if (maxLength instanceof Integer)
		{
			if (value.length() > maxLength.intValue())
			{
				throw new PropertyValidationException("Length of value '" + value + "' is greater than the maximum allowed length of " + maxLength + " characters", aValue);
			}
		}
		
		// check if given value is contained in the list
		if (picklistOnly)
		{
			if (picklist.contains(value) == false)
			{
				throw new PropertyValidationException("Value '" + value + "' is not in the list of allowed values", aValue);
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public Object clone() 
	{
	    try 
	    {
	        StringValidator result = (StringValidator) super.clone();
	    
	        result.picklist = (Set<String>)((HashSet )this.picklist).clone();
	        
	        return result;
	    } 
	    catch (CloneNotSupportedException e) 
	    {
	        return null;
	    }
	}
	
}
