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

import java.io.Serializable;
import java.util.List;

import org.janux.bus.search.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trg.search.Search;

/**
 * 
 * @author albertobuffagni@gmail.com
 *
 */
public class MerchantAccountServiceImpl implements MerchantAccountService<SearchCriteria>  {

	public Logger log = LoggerFactory.getLogger(this.getClass());
	
	private BusinessUnitService<SearchCriteria> businessUnitService;
	private MerchantAccountDao<MerchantAccount, Serializable, SearchCriteria> merchantAccountDao;
	
	public MerchantAccount find(SearchCriteria searchCriteria) {
		List<MerchantAccount> list = getMerchantAccountDao().findByCriteria(searchCriteria);
		MerchantAccount merchantAccount = null; 
		if (!list.isEmpty())
			merchantAccount = list.get(0);
		
		return merchantAccount;
	}

	public void delete(String uuid) {
		log.debug("Deleting merchantAccount with uuid: "+ uuid);
		
		if (uuid==null)
			throw new RuntimeException("uuid is null");
				
		Search searchCriteria = new Search(MerchantAccountImpl.class);
		searchCriteria.addFilterEqual("uuid",uuid);
		
		MerchantAccount merchantAccount =  find(searchCriteria);
		delete(merchantAccount);
	}

	public void delete(MerchantAccount merchantAccount) {
		log.debug("Deleting merchantAccount with uuid: "+ merchantAccount);

		if (merchantAccount!=null)
		{
			String uuid = merchantAccount.getUuid();
			if (canBeDeleted(uuid))
			{
				getMerchantAccountDao().delete(merchantAccount);
				log.debug("Deleted merchantAccount with uuid: "+ uuid);
			}
			else
			{
				merchantAccount.setEnabled(false);
				getMerchantAccountDao().saveOrUpdate(merchantAccount);
				log.debug("Disabled merchantAccount with uuid: "+ uuid);
			}
		}
	}

	private boolean canBeDeleted(String uuid) {
		log.debug("Asking if the merchant account can be deleted with uuid: "+ uuid);
		
		if (uuid==null)
			throw new RuntimeException("uuid is null");
				
		log.debug("Looking for transactions for this merchant account");
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("merchantAccount.uuid", uuid);
		
		List<BusinessUnit> list = getBusinessUnitService().findByCriteria(searchCriteria);
		if (!list.isEmpty())
		{
			log.debug("The merchant account has businessUnit, can not be deleted");
			return false;
		}
		
		return true;
	}
	
	public MerchantAccount save(MerchantAccount merchantAccount)
	{
		return getMerchantAccountDao().saveOrUpdate(merchantAccount);
	}

	public void setBusinessUnitService(BusinessUnitService<SearchCriteria> businessUnitService) {
		this.businessUnitService = businessUnitService;
	}

	public BusinessUnitService<SearchCriteria> getBusinessUnitService() {
		return businessUnitService;
	}

	public void setMerchantAccountDao(MerchantAccountDao<MerchantAccount, Serializable, SearchCriteria> merchantAccountDao) {
		this.merchantAccountDao = merchantAccountDao;
	}

	public MerchantAccountDao<MerchantAccount, Serializable, SearchCriteria> getMerchantAccountDao() {
		return merchantAccountDao;
	}

	
}
