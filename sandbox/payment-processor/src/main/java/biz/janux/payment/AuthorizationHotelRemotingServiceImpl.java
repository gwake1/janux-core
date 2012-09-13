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

import java.util.Iterator;
import java.util.List;

import org.janux.bus.search.SearchCriteria;

public class AuthorizationHotelRemotingServiceImpl extends RemotingServiceImpl implements AuthorizationHotelRemotingService{

	private AuthorizationHotelService<AuthorizationHotel> authorizationHotelService;
	
	private CreditCardStorageService  creditCardStorageService;
	
	private BusinessUnitService<SearchCriteria>  businessUnitService;
	
	public List<AuthorizationHotel> findAuthorizationsActive(String businessUnitUuid, String creditCardUuid) {
		List<AuthorizationHotel> list = getAuthorizationHotelService().findAuthorizationsActive(businessUnitUuid, creditCardUuid);
		for (Iterator<AuthorizationHotel> iterator = list.iterator(); iterator.hasNext();) {
			AuthorizationHotel authorizationHotel = (AuthorizationHotel) iterator.next();
			getTranslatorObjectService().translate(authorizationHotel);
		}
		return list; 
	}

	public boolean isAuthorizationExist(String businessUnitUuid, String creditCardUuid) {
		return getAuthorizationHotelService().isAuthorizationExist(businessUnitUuid, creditCardUuid);
	}
	
	public AuthorizationHotel saveNew(AuthorizationHotel authorization, String businessUnitUuid, String creditCardUuid,AuthorizationType authorizationType) {
		CreditCard creditCard = getCreditCardStorageService().load(creditCardUuid,false);
		BusinessUnit businessUnit = getBusinessUnitService().load(businessUnitUuid);
		
		authorization.setCreditCard(creditCard);
		authorization.setBusinessUnit(businessUnit);
		
		authorization = getAuthorizationHotelService().create(authorization, authorization.getAmount(), authorization.getTransactionResponse(), authorizationType);
		return getTranslatorObjectService().translate(authorization);
	}

	public void setAuthorizationHotelService(AuthorizationHotelService<AuthorizationHotel> authorizationHotelService) {
		this.authorizationHotelService = authorizationHotelService;
	}

	public AuthorizationHotelService<AuthorizationHotel> getAuthorizationHotelService() {
		return authorizationHotelService;
	}

	public void setCreditCardStorageService(CreditCardStorageService creditCardStorageService) {
		this.creditCardStorageService = creditCardStorageService;
	}

	public CreditCardStorageService getCreditCardStorageService() {
		return creditCardStorageService;
	}

	public void setBusinessUnitService(BusinessUnitService<SearchCriteria> businessUnitService) {
		this.businessUnitService = businessUnitService;
	}

	public BusinessUnitService<SearchCriteria> getBusinessUnitService() {
		return businessUnitService;
	}

}
