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

import org.hibernate.Hibernate;
import org.janux.bus.persistence.GenericDaoWithFacetsHibImpl;
import org.janux.bus.search.SearchCriteria;

public class SettlementItemHotelDaoHibImpl<T extends SettlementItemHotelImpl, ID extends Serializable, S extends SearchCriteria,F extends Enum<F>> extends GenericDaoWithFacetsHibImpl<T, ID, S,F> 
implements SettlementItemHotelDao<T,ID,S,F> {

	public void initialize(T persistentObject, F facet) {
		if (facet.equals(SettlementItemFacet.AUTHORIZATION))
			Hibernate.initialize(persistentObject.getAuthorization());
		if (facet.equals(SettlementItemFacet.CREDIT_CARD))
			Hibernate.initialize(persistentObject.getCreditCard());
		if (facet.equals(SettlementItemFacet.BUSINESS_UNIT))
			Hibernate.initialize(persistentObject.getBusinessUnit());
	}

}
