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

package biz.janux.geography.controllers;

import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import biz.janux.geography.GeographyService;

public class CountryControllerRestImpl {

	private	GeographyService geographyService;
	
	@RequestMapping(params = "countries", method = RequestMethod.GET)
    public String loadCountries(ModelMap modelMap)
	{
		Map countries = getGeographyService().loadAllCountries();
		modelMap.addAttribute("countries",countries.values());
        return "json";
	}

	public GeographyService getGeographyService() {
		return geographyService;
	}

	public void setGeographyService(GeographyService geographyService) {
		this.geographyService = geographyService;
	}

}
