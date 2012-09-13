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

import java.net.URL;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.janux.bus.persistence.PersistentAbstract;

import biz.janux.people.Party;

/**
 ***************************************************************************************************
 * Simple bean that stores an absolute address to an http resource, such as a
 * web site address.
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.9 $ - $Date: 2007-12-06 01:20:41 $
 ***************************************************************************************************
 */
public class HttpAddressImpl extends PersistentAbstract implements Url
{
	private static final long serialVersionUID = 20071205;

	private String address;

	/** plain vanilla constructor */
	public HttpAddressImpl() {}

	public HttpAddressImpl(String address) {
		this.setAddress(address);
	}



	/** an absolute web site address, without the 'http://' prefix */
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	
	/** 
	 * prepends 'http://' to the address and returns the corresponding Uniform Resource Identifier
	 */
	public URI getUri() throws URISyntaxException 
	{
		if (this.getAddress() instanceof String)
		{
			final URI uri = new URI("http://" + this.getAddress());
			return (uri);
		}
		else
		{
			return (null);
		}
	}


	/** 
	 * prepends 'http://' to the address and returns the corresponding Uniform Resource Locator
	 */
	public URL getUrl() throws MalformedURLException 
	{
		if (this.getAddress() instanceof String)
		{
			final URL url = new URL("http://" + this.getAddress());
			return (url);
		}
		else
		{
			return (null);
		}
	}

	@SuppressWarnings("unchecked")
	public Object clone() 
	{
		try 
		{
			HttpAddressImpl result = (HttpAddressImpl) super.clone();
		
			result.setId(-1);
			
			return result;
		} 
		catch (CloneNotSupportedException e) 
		{
			return null;
		}
	}
	
	

} // end class HttpAddressImpl
