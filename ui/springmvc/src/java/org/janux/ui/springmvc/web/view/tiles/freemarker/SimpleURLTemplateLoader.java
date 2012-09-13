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

package org.janux.ui.springmvc.web.view.tiles.freemarker;

import java.net.MalformedURLException;
import java.net.URL;

import freemarker.cache.URLTemplateLoader;

/*
 * Support including FreeMarker templates from web URLs. It's surprising to me that there's
 * not better built-in support for this.
 * The existing FreeMarker flow is kind of weird. FreeMarker will actually barf
 * if getURL() return a value, but the URL doesn't exist. This is especially annoying
 * because FreeMarker does locale-checking (e.g. looks for filename_en_US and filename_en).
 * This can be turned off, but we may want to take advantage of it to some extent. Ugh.
 * 
 * So, I've implemented a simple approach where you specify a URL base in the config, and
 * then use the full URL in your #include or #import statement. This limits the barfing to
 * files that are at least being deliberately referenced. For the moment, though, clients
 * will have to make sure to provide the proper locale suffix.
 */
public class SimpleURLTemplateLoader extends URLTemplateLoader
{
	String urlBase;
	
	public SimpleURLTemplateLoader(String urlBase) throws MalformedURLException
	{
		this.urlBase = urlBase;
	}
	
	@Override
	protected URL getURL(String urlStr)
	{
		URL url = null;
		
		try
		{
			if (urlStr.startsWith(urlBase))
			{
				url = new URL(urlStr);
			}
		}
		catch (MalformedURLException e)
		{
			// nothing to do?
		}

		return url;
	}
}
