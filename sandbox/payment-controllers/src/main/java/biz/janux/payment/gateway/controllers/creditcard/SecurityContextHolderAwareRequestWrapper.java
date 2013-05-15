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

package biz.janux.payment.gateway.controllers.creditcard;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

/**
 * It is a wrapper of {@link SecurityContextHolderAwareRequestWrapper}
 * 
 * Allow change the parameters of security requests 
 * 
 * @author albertobuffagni@gmail.com
 * 
 */
public class SecurityContextHolderAwareRequestWrapper extends org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper {

	private final Map<String, String[]> parameters = new LinkedHashMap<String, String[]>(16);

	public SecurityContextHolderAwareRequestWrapper(HttpServletRequest request,String rolePrefix) {
		super(request,rolePrefix);
		
		final Enumeration<String> iter = super.getParameterNames();
		while (iter.hasMoreElements()) {
			final String p = iter.nextElement();
			parameters.put(p, new String[]{super.getParameter(p)});
		}
	}

	public String getParameter(String name) {
		Assert.notNull(name, "Parameter name must not be null");
		String[] arr = this.parameters.get(name);
		return (arr != null && arr.length > 0 ? arr[0] : null);
	}

	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(this.parameters.keySet());
	}

	public String[] getParameterValues(String name) {
		Assert.notNull(name, "Parameter name must not be null");
		return this.parameters.get(name);
	}

	public Map<String, String[]> getParameterMap() {
		return this.parameters;
	}
	
}
