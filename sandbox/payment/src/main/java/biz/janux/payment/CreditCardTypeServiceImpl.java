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

import java.util.List;
import org.janux.bus.search.SearchCriteria;
import com.trg.search.Search;

public class CreditCardTypeServiceImpl implements CreditCardTypeService<SearchCriteria>  {

	private CreditCardTypeDao<CreditCardType, Integer, SearchCriteria> creditCardTypeDao;
	
	public CreditCardType findFirstByCriteria(SearchCriteria searchCriteria) {
		((Search) searchCriteria).setMaxResults(1);

		List<CreditCardType> list = getCreditCardTypeDao().findByCriteria(searchCriteria);

		if (!list.isEmpty()) {
			CreditCardType creditCardType = list.get(0);
			return creditCardType;
		}

		return null;
	}

	public CreditCardType findByCode(String code) {
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("code", code);
		return findFirstByCriteria(searchCriteria);
	}

	public List<CreditCardType> findAll() {
		Search searchCriteria = new Search();
		return getCreditCardTypeDao().findByCriteria(searchCriteria);
	}

	public CreditCardTypeDao<CreditCardType, Integer, SearchCriteria> getCreditCardTypeDao() {
		return creditCardTypeDao;
	}

	public void setCreditCardTypeDao(CreditCardTypeDao<CreditCardType, Integer, SearchCriteria> creditCardTypeDao) {
		this.creditCardTypeDao = creditCardTypeDao;
	}

}
