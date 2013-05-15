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

package biz.janux.people.net;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.janux.bus.persistence.PersistentAbstract;

import biz.janux.people.Party;

/**
 ***************************************************************************************************
 * bean that represents an Email Address Uniform Resource Identifier
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.9 $ - $Date: 2007-12-06 01:20:41 $
 ***************************************************************************************************
 */
public class EmailAddressImpl extends PersistentAbstract implements Uri
{
	private static final long serialVersionUID = 20071205;

	private String address;

	/** plain vanilla constructor */
	public EmailAddressImpl() {}

	public EmailAddressImpl(String address) {
		this.setAddress(address);
	}


	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	
	public URI getUri() throws URISyntaxException 
	{
		if (this.getAddress() == null)
		{
			return (null);
		}
		else
		{
			final URI uri = new URI("mailto:" + this.getAddress());
			return uri;
		}
	}

	@SuppressWarnings("unchecked")
	public Object clone() 
	{
		try 
		{
			EmailAddressImpl result = (EmailAddressImpl) super.clone();
		
			result.setId(-1);
			
			return result;
		} 
		catch (CloneNotSupportedException e) 
		{
			return null;
		}
	}
	

} // end class EmailAddressImpl
