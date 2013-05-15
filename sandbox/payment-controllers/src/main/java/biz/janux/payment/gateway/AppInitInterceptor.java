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

package biz.janux.payment.gateway;

import static biz.janux.payment.gateway.AppStatus.*;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 ***************************************************************************************************
 * This interceptor checks to see whether the application has been properly initialized with the
 * Password Encryption Key, and if not, redirects to a page with an error message
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4.0 - 2012-02-05
 ***************************************************************************************************
 */
public class AppInitInterceptor extends HandlerInterceptorAdapter
{
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "appStatus")
	protected Properties appStatus;

	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException
	{
		Boolean isInit = Boolean.parseBoolean(appStatus.getProperty(IS_INITIALIZED));

		if (isInit) {
			return true;
		} else {
			String initErrorView = request.getContextPath() + "/" + this.appStatus.getProperty(VIEW_INIT_ERROR);
			/*
			log.debug("servletPath: {}", request.getServletPath());
			log.debug("contextPath: {}", request.getContextPath());
			log.debug("pathInfo: {}", request.getPathInfo());
			*/
			log.warn("Application has not been initialized, redirecting to init_error view: {}", initErrorView);
			response.sendRedirect(initErrorView);
			return false;
		}
	}
} // end class
