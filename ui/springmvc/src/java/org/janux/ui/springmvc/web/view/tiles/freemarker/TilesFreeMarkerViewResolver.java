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

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.janux.ui.springmvc.web.view.tiles.AbstractTilesSpringViewResolver;

/**
 * Resolver for using Tiles and FreeMarker together. 
 * 
 * @author <a href="mailto: kirlen@janux.org">Kevin Irlen</a>
 */
public class TilesFreeMarkerViewResolver extends AbstractTilesSpringViewResolver
{

	/**
	 * Sets default viewClass to TilesFreeMarkerView.
	 * @see #setViewClass
	 */
	public TilesFreeMarkerViewResolver()
	{
		setViewClass(TilesFreeMarkerView.class);
	}

	/**
	 * Requires TilesFreeMarkerView.
	 * @see TilesFreeMarkerView
	 */
	protected Class requiredViewClass()
	{
		return TilesFreeMarkerView.class;
	}

	protected View loadView(String viewName, Locale locale) throws Exception
	{
		TilesFreeMarkerView view = (TilesFreeMarkerView) super.loadView(viewName, locale);
		view.setExposeRequestAttributes(true);
		view.setExposeSessionAttributes(true);

		return view;
	}

}
