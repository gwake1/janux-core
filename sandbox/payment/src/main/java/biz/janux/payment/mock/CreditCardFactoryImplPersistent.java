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

import java.util.Date;

import org.janux.bus.search.SearchCriteria;

import biz.janux.geography.PostalAddress;
import biz.janux.geography.PostalAddressImpl;

import biz.janux.payment.*;

import com.trg.search.Search;

public class CreditCardFactoryImplPersistent implements CreditCardFactory{

	private CreditCardTypeService<SearchCriteria> creditCardTypeService; 
	
	public CreditCard getCreditCardVisaSwiped() {
		CreditCard creditCard = new CreditCardImpl();
		
		PostalAddress postalAddress = getPostalAddress();
		
		creditCard.setBillingAddress(postalAddress);
		creditCard.setCardholderName(CreditCardConstants.CARD_HOLDER_NAME);
		creditCard.setExpirationDate(new Date());
		creditCard.setNumber(CreditCardConstants.NUMBER_VISA);
		creditCard.setTrack2(CreditCardConstants.TRACK2_VISA);
		creditCard.setSwiped(true);
		
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("code", CreditCardTypeEnum.VISA.getCode());
		CreditCardType creditCardType = getCreditCardTypeService().findFirstByCriteria(searchCriteria);

		creditCard.setType(creditCardType);
		
		return creditCard;
	}
	
	public CreditCard getCreditCardVisa() {
		CreditCard creditCard = new CreditCardImpl();
		
		PostalAddress postalAddress = getPostalAddress();
		
		creditCard.setBillingAddress(postalAddress);
		creditCard.setCardholderName(CreditCardConstants.CARD_HOLDER_NAME);
		creditCard.setExpirationDate(new Date());
		creditCard.setNumber(CreditCardConstants.NUMBER_VISA);
		creditCard.setSwiped(false);
		
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("code", CreditCardTypeEnum.VISA.getCode());
		CreditCardType creditCardType = getCreditCardTypeService().findFirstByCriteria(searchCriteria);

		creditCard.setType(creditCardType);
		
		return creditCard;
	}

	/**
	 * @return
	 */
	private PostalAddress getPostalAddress() {
		PostalAddress postalAddress = new PostalAddressImpl();
		postalAddress.setCityAsString(CreditCardConstants.CITY);
		postalAddress.setCountryAsString(CreditCardConstants.COUNTRY_STRING);
		postalAddress.setLine1(CreditCardConstants.ADDRESS);
		postalAddress.setPostalCode(CreditCardConstants.POSTAL_CODE);
		postalAddress.setStateProvinceAsString(CreditCardConstants.STATE_STRING);
		return postalAddress;
	}
	
	public CreditCard getCreditCardAmericanExpress() {
		CreditCard creditCard = new CreditCardImpl();
		
		PostalAddress postalAddress = getPostalAddress();
		
		creditCard.setBillingAddress(postalAddress);
		creditCard.setCardholderName(CreditCardConstants.CARD_HOLDER_NAME);
		creditCard.setExpirationDate(new Date());
		creditCard.setNumber(CreditCardConstants.NUMBER_AMERICAN_EXPRESS);
		creditCard.setSwiped(false);
		
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("code", CreditCardTypeEnum.AMERICAN_EXPRESS.getCode());
		CreditCardType creditCardType = getCreditCardTypeService().findFirstByCriteria(searchCriteria);

		creditCard.setType(creditCardType);
		
		return creditCard;
	}

	public void setCreditCardTypeService(CreditCardTypeService<SearchCriteria> creditCardTypeService) {
		this.creditCardTypeService = creditCardTypeService;
	}

	public CreditCardTypeService<SearchCriteria> getCreditCardTypeService() {
		return creditCardTypeService;
	}

	public CreditCard getCreditCard(String arg0, String arg1, Date arg2, String arg3, PostalAddress arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	public CreditCard getCreditCardCompleteVisa() {
		// TODO Auto-generated method stub
		return null;
	}

	public CreditCard getCreditCardSavedVisa() {
		// TODO Auto-generated method stub
		return null;
	}

	public CreditCard getCreditCardSavedVisaSwiped() {
		// TODO Auto-generated method stub
		return null;
	}

}

