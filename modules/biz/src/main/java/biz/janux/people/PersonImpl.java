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


//import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 ***************************************************************************************************
 * Represents a Person in a variety of contexts
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.8 $ - $Date: 2007-05-01 23:20:58 $
 ***************************************************************************************************
 */
public class PersonImpl extends PartyAbstract implements Person
{
	private static final long serialVersionUID = 6924147515503069793L;
	private PersonName name;


	/** plain vanilla constructor */
	public PersonImpl() {}


	public PersonName getName() 
	{
		if(this.name == null)
			this. name = new PersonNameImpl();

		return this.name;
	}
	
	public void setName(PersonName name) {
		this.name = name;
	}


	public PartyName getPartyName() {
		return this.getName();
	}
	
	/*
	public void setPartyName(PartyName partyName) {
		// do nothing;
	}
	*/

	public String toString() 
	{
		return new ToStringBuilder(this)
			.append("name", getName())
			.toString();
	}

	public Object clone() 
	{
        PersonImpl result = (PersonImpl) super.clone();
        if (this.name != null)
        {
            result.name = (PersonName )this.name.clone();
        }
        return result;
	}
	
	
} // end class PersonImpl
