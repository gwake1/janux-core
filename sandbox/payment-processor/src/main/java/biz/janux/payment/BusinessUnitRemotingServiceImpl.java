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

package biz.janux.payment;

import org.janux.bus.search.SearchCriteria;

import com.trg.search.Search;


public class BusinessUnitRemotingServiceImpl extends RemotingServiceImpl implements BusinessUnitRemotingService {

	BusinessUnitService<SearchCriteria> businessUnitService;
	
	MerchantAccountService<SearchCriteria> merchantAccountService;

	public void delete(String uuid) {
		getBusinessUnitService().delete(uuid);
	}

	public BusinessUnit save(BusinessUnit remoteBusinessUnit,String uuid) {
		BusinessUnit businessUnit;
		MerchantAccount merchantAccount;
		
		if (uuid==null)
		{
			businessUnit = getBusinessUnitService().resolveBusinessUnit(remoteBusinessUnit.getCode(), true, remoteBusinessUnit.getIndustryType());
			businessUnit.setName(remoteBusinessUnit.getName());
		}
		else		
		{
			/**
			 * update the business unit
			 */
			businessUnit = getBusinessUnitService().load(uuid);
			
			if (businessUnit==null)
				throw new IllegalStateException("The business unit is not exist with uuid "+uuid);
			
			if (!businessUnit.isEnabled())
				throw new IllegalStateException("The business unit is with uuid "+uuid+" is disabled");
			
			businessUnit.setIndustryType(remoteBusinessUnit.getIndustryType());
			businessUnit.setName(remoteBusinessUnit.getName());
			businessUnit.setCode(remoteBusinessUnit.getCode());
		}
		
		if (remoteBusinessUnit.getMerchantAccount()!=null)
		{
			merchantAccount = getMerchantAccount(remoteBusinessUnit.getMerchantAccount());
	
			/**
			 * Set the correct Merchant account
			 */
			businessUnit.setMerchantAccount(merchantAccount);
		}
		
		return getBusinessUnitService().save(businessUnit);
	}

	/**
	 * Get the correct merchant account. 
	 * Validate if it has to be created as new, or it is existing.
	 * Update the merchant account with the new data.
	 * 
	 * @param remoteBusinessUnit
	 * @return
	 */
	private MerchantAccount getMerchantAccount(MerchantAccount remoteMerchantAccount) {
		MerchantAccount merchantAccount;
		
		if (remoteMerchantAccount==null)
			throw new RuntimeException("remoteMerchantAccount is null");
		
		if (remoteMerchantAccount.getNumber()==null)
			throw new RuntimeException("remoteMerchantAccount.getNumber() is null:{}");
		
		/**
		 * find if exist the merchant account
		 */
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("number",remoteMerchantAccount.getNumber());
		
		merchantAccount = getMerchantAccountService().find(searchCriteria);
		if (merchantAccount==null)
		{
			/**
			 * the merchant account has to be created
			 */
			merchantAccount = remoteMerchantAccount;
		}
		else{
			
			if (!merchantAccount.isEnabled())
				throw new IllegalStateException("The merchant account is with number "+remoteMerchantAccount.getNumber()+" is disabled");
			
			merchantAccount.setAcquiringBankBin(remoteMerchantAccount.getAcquiringBankBin());
			merchantAccount.setAgentBankNum(remoteMerchantAccount.getAgentBankNum());
			merchantAccount.setAgentChainNum(remoteMerchantAccount.getAgentChainNum());
			merchantAccount.setCardholderServicePhone(remoteMerchantAccount.getCardholderServicePhone());
			merchantAccount.setMerchantPhone(remoteMerchantAccount.getMerchantPhone());
			merchantAccount.setName(remoteMerchantAccount.getName());
			merchantAccount.setNumber(remoteMerchantAccount.getNumber());
			merchantAccount.setStoreNum(remoteMerchantAccount.getStoreNum());
			merchantAccount.setTerminalId(remoteMerchantAccount.getTerminalId());
			merchantAccount.setTerminalNum(remoteMerchantAccount.getTerminalNum());
			merchantAccount.getMerchantAddress().setCityAsString(remoteMerchantAccount.getMerchantAddress().getCityAsString());
			merchantAccount.getMerchantAddress().setStateProvinceAsString(remoteMerchantAccount.getMerchantAddress().getStateProvinceAsString());
			merchantAccount.getMerchantAddress().setPostalCode(remoteMerchantAccount.getMerchantAddress().getPostalCode());
		}
		
		merchantAccount = getMerchantAccountService().save(merchantAccount);
		return merchantAccount;
	}
	
	public BusinessUnitService<SearchCriteria> getBusinessUnitService() {
		return businessUnitService;
	}

	public void setBusinessUnitService(BusinessUnitService<SearchCriteria> businessUnitService) {
		this.businessUnitService = businessUnitService;
	}

	public MerchantAccountService<SearchCriteria> getMerchantAccountService() {
		return merchantAccountService;
	}

	public void setMerchantAccountService(MerchantAccountService<SearchCriteria> merchantAccountService) {
		this.merchantAccountService = merchantAccountService;
	}

}
