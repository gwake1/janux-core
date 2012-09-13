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

import org.janux.bus.search.SearchCriteria;

import api.org.janux.bus.service.GenericServiceImpl;

import com.trg.search.Search;

public class SettlementItemHotelServiceImpl <T extends SettlementItemHotelImpl,ID extends Serializable,DW extends SettlementItemHotelDao<T, ID, SearchCriteria,SettlementItemFacet>, DR extends SettlementItemHotelDao<T, ID, SearchCriteria,SettlementItemFacet>>
extends GenericServiceImpl<T,ID,SearchCriteria,DW,DR> 
implements SettlementItemHotelService<T>  {

	private
	AuthorizationHotelService<AuthorizationHotel> authorizationHotelService;
	
	public T findByUuid(String uuid) {
		
		if (uuid==null)
			throw new RuntimeException("uuid null or empty");
		
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("uuid", uuid);
		
		return find(searchCriteria);
	}

	public T delete(String uuid) {
		T settlementItemHotel = findByUuid(uuid);
		
		if (settlementItemHotel.getSettlement()!=null)
			throw new IllegalStateException ("The settement item can not be deleted because it was captured");
		
		if (settlementItemHotel.getAuthorization()!=null)
		{
			getAuthorizationHotelService().unBatched((AuthorizationHotel)settlementItemHotel.getAuthorization());
		}
		
		super.delete(settlementItemHotel);
		return settlementItemHotel;
	}

	public void setAuthorizationHotelService(AuthorizationHotelService<AuthorizationHotel> authorizationHotelService) {
		this.authorizationHotelService = authorizationHotelService;
	}

	public AuthorizationHotelService<AuthorizationHotel> getAuthorizationHotelService() {
		return authorizationHotelService;
	}

}
