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

package org.janux.ui.web;

import java.util.Map;

import freemarker.template.Configuration;


/**
 ***************************************************************************************************
 * Implementation of FreemarkerObjectFormatter where it is desirable to serialize a set of objects 
 * rather than a single object, and where the model is created outside of this formatter.
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.1 - 2011-04-06
 ***************************************************************************************************
 */
public class ModelFormatter extends FreemarkerObjectFormatter<Map<String,Object>>
{
	public ModelFormatter(Configuration config, String aTemplateName) 
	{
		if (config == null) throw new IllegalArgumentException("Attempting to initialize " + this.getClass().getName() + " with null Freemarker Configuration");
		this.setFreemarkerConfig(config);

		if (aTemplateName == null) throw new IllegalArgumentException("Attempting to initialize " + this.getClass().getName() + " with null Template Name");
		this.setTemplateName(aTemplateName);
	}

	public Map<String,Object> buildModel(Map<String, Object> model) {
		return model;
	}

} // end class


