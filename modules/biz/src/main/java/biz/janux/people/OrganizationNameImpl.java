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


//import org.apache.commons.logging.LogFactory;
//import org.apache.commons.logging.Log;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 ***************************************************************************************************
 * Represents the name of an Organization
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.5 $ - $Date: 2006-11-14 01:30:39 $
 ***************************************************************************************************
 */
public class OrganizationNameImpl extends PartyNameImpl implements OrganizationName
{
	private static final long serialVersionUID = -7677028713664500251L;
	private String shortName;
	private String longName;
	private String legal;


	/** plain vanilla constructor */
	public OrganizationNameImpl() {}


	public String getShort() {
		return this.shortName;
	}
	
	public void setShort(String s) {
		this.shortName = s;
	}


	public String getLong() {
		return this.longName;
	}
	
	public void setLong(String s) {
		this.longName = s;
	}


	public String getLegal() {
		return this.legal;
	}
	
	public void setLegal(String legal) {
		this.legal = legal;
	}


	public String toString() 
	{
		return new ToStringBuilder(this)
			.append("short", getShort())
			.append("long",  getLong())
			.append("legal", getLegal())
			.toString();
	}

	public Object clone() 
	{
        OrganizationNameImpl result = (OrganizationNameImpl) super.clone();
        return (result);
	}
	
} // end class OrganizationNameImpl
