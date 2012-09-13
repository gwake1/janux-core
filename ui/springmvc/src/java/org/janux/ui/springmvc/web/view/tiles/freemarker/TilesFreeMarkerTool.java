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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.janux.ui.springmvc.web.view.tiles.TilesTool;
import org.springframework.context.ApplicationContext;

import freemarker.template.*;

/**
 * <p>View tool to use struts-tiles with FreeMarker</p>
 * <p><pre>
 * Template example(s):
 *  &lt;!-- insert a tile --&gt;
 *  ${tiles.myTileDefinition}
 *
 *  &lt;!-- get named attribute value from the current tiles-context --&gt;
 *  ${tiles.getAttribute("myTileAttribute")}
 *
 *  &lt;!-- import all attributes of the current tiles-context into the velocity-context. --&gt;
 *  ${tiles.importAttributes()}
 *
 * @author <a href="mailto:kirlen@janux.org">Kevin Irlen</a>
 */
public class TilesFreeMarkerTool extends TilesTool
{
	Configuration configuration;
	Map model;
	
	public TilesFreeMarkerTool(HttpServletRequest request,
			HttpServletResponse response, ApplicationContext applicationContext, Configuration configuration,Map model)
	{
		super(request, response, applicationContext);
		
		this.configuration = configuration;
		this.model = model;
	}

	protected void putAttribute(String name, Object value)
	{
		model.put(name,value);
	}

	protected String doRender(String page) throws IOException, TemplateException
	{
		String result = null;
		
		try {
		if (page.endsWith(".ftl"))
		{
			Template template = configuration.getTemplate(page);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			template.process(model, pw);
			
			result = sw.getBuffer().toString();
		}
		} catch (TemplateException e)
		{
			e.printStackTrace();
			throw e;
		}
	
	return result;
	}

}
