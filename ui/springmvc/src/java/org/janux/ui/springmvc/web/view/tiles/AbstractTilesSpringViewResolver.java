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

package org.janux.ui.springmvc.web.view.tiles;

import java.util.Locale;

import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Abstract Spring view resolver class for combining Tiles and your favorite view technology</p>
 * @author <a href="mailto:kirlen@janux.org">Kevin Irlen</a>
 */
public abstract class AbstractTilesSpringViewResolver extends UrlBasedViewResolver
{
	Log log = LogFactory.getLog(this.getClass());

	protected boolean exposeSpringMacroHelpers = false;
	protected Map environmentVariables;

	/**
	 * Used to expose environment variables defined in the spring configuration;
	 * these environment variables are passed to the view when the resolver
	 * instantiates the view in loadView
	 */
	public Map getEnvironmentVariables() {
		return this.environmentVariables;
	}

	public void setEnvironmentVariables(Map map) {
		if (log.isDebugEnabled()) log.debug("Setting Environment Variables map: '" + map + "'");
		this.environmentVariables = map;
	}


	/** set to true if you want to use the spring html-generating macros */
	public void setExposeSpringMacroHelpers(boolean expose)
	{
		this.exposeSpringMacroHelpers = expose;
	}

	protected View loadView(String viewName, Locale locale) throws Exception 
	{
		AbstractTilesView view = (AbstractTilesView) super.loadView(viewName, locale);
		view.setExposeSpringMacroHelpers(this.exposeSpringMacroHelpers);
		view.setEnvironmentVariables(this.environmentVariables);

		return view;
	}

}
