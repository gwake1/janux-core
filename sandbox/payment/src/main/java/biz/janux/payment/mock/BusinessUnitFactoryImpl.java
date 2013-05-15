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

package biz.janux.payment.mock;

import org.springframework.beans.factory.annotation.Autowired;

import biz.janux.payment.BusinessUnit;
import biz.janux.payment.BusinessUnitImpl;
import biz.janux.payment.IndustryType;
import biz.janux.payment.MerchantAccount;
import biz.janux.payment.MerchantAccountFactory;

public class BusinessUnitFactoryImpl implements BusinessUnitFactory{

	@Autowired
	MerchantAccountFactory merchantAccountFactory;
	
	public BusinessUnit getBusinessUnit() {
		
		BusinessUnit businessUnit = new BusinessUnitImpl();
		businessUnit.setIndustryType(BusinessUnitConstants.INDUSTRY_TYPE);
		businessUnit.setName(BusinessUnitConstants.NAME);
		businessUnit.setCode(BusinessUnitConstants.CODE);
		
		MerchantAccount merchantAccount = getMerchantAccountFactory().getMerchantAccount();

		businessUnit.setEnabled(true);
		businessUnit.setMerchantAccount(merchantAccount);

		return businessUnit;
		
	}

	public BusinessUnit getVitalBusinessUnit() {
		BusinessUnit businessUnit = new BusinessUnitImpl();
		businessUnit.setIndustryType(BusinessUnitConstants.INDUSTRY_TYPE);
		businessUnit.setName(BusinessUnitConstants.NAME);
		businessUnit.setCode(BusinessUnitConstants.CODE);
		
		MerchantAccount merchantAccount = getMerchantAccountFactory().getVitalMerchantAccount();
		businessUnit.setEnabled(true);
		businessUnit.setMerchantAccount(merchantAccount);
		
		return businessUnit;
	}

	public MerchantAccountFactory getMerchantAccountFactory() {
		return merchantAccountFactory;
	}

	public void setMerchantAccountFactory(MerchantAccountFactory merchantAccountFactory) {
		this.merchantAccountFactory = merchantAccountFactory;
	}

}
