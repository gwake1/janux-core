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

import java.util.List;

import org.janux.bus.search.SearchCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import biz.janux.payment.CreditCardType;
import biz.janux.payment.CreditCardTypeService;

public class CreditCardTypeControllerRestImpl {

	private	CreditCardTypeService<SearchCriteria> creditCardTypeService;
	
	@RequestMapping(params = "creditCardTypes", method = RequestMethod.GET)
    public String loadCreditCardTypes(ModelMap modelMap)
	{
		List<CreditCardType> creditCardTypes = getCreditCardTypeService().findAll();
		modelMap.addAttribute("creditCardTypes",creditCardTypes);
        return "json";
	}

	public CreditCardTypeService<SearchCriteria> getCreditCardTypeService() {
		return creditCardTypeService;
	}

	public void setCreditCardTypeService(CreditCardTypeService<SearchCriteria> creditCardTypeService) {
		this.creditCardTypeService = creditCardTypeService;
	}
	
}
