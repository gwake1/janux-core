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

import org.janux.bus.persistence.Identifiable;

import biz.janux.geography.Country;
import biz.janux.geography.CountryImpl;
import biz.janux.geography.PostalAddress;
import biz.janux.geography.PostalAddressImpl;
import biz.janux.geography.StateProvince;
import biz.janux.geography.StateProvinceImpl;
import biz.janux.payment.*;

import java.text.SimpleDateFormat;

public class CreditCardFactoryImpl implements CreditCardFactory{

	public CreditCard getCreditCardVisa() {		
		PostalAddress postalAddress = getPostalAddress();
		return getCreditCard(CreditCardConstants.CARD_HOLDER_NAME,CreditCardConstants.NUMBER_VISA, CreditCardConstants.EXPIRATION_DATE, CreditCardTypeEnum.VISA.getCode(),postalAddress);
	}
	
	public CreditCard getCreditCard(String holderName,String number,Date expirationDate,String typeCode,PostalAddress postalAddress) {
		CreditCard creditCard = new CreditCardImpl();
		
		creditCard.setBillingAddress(postalAddress);
		creditCard.setCardholderName(holderName);
		creditCard.setExpirationDate(expirationDate);
		creditCard.setNumber(number);
		
		CreditCardType creditCardType;
		creditCardType = new CreditCardTypeImpl();
		creditCardType.setCode(typeCode);
		
		creditCard.setType(creditCardType);
		
		creditCard.setSwiped(false);
		
		return creditCard;
	}

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
		PostalAddress postalAddress = getPostalAddress();
		return getCreditCard(CreditCardConstants.CARD_HOLDER_NAME,CreditCardConstants.NUMBER_AMERICAN_EXPRESS,new Date(),CreditCardTypeEnum.AMERICAN_EXPRESS.getCode(),postalAddress);
	}

	public CreditCard getCreditCardSavedVisa() {
		CreditCard creditCard = getCreditCardVisa();
		creditCard.setNumber(null);
		((Identifiable)creditCard).setUuid(CreditCardConstants.UUID);
		creditCard.setNumberMasked(CreditCardConstants.NUMBER_VISA_MASKED);
		creditCard.setToken(CreditCardConstants.CREDIT_CARD_VISA_TOKEN);
		creditCard.setEnabled(true);
		creditCard.setSwiped(false);
		return creditCard;
	}
	
	public CreditCard getCreditCardCompleteVisa() {
		CreditCard creditCard = getCreditCardVisa();
		((Identifiable)creditCard).setUuid(CreditCardConstants.UUID);
		creditCard.setNumberMasked(CreditCardConstants.NUMBER_VISA_MASKED);
		return creditCard;
	}

	public CreditCard getCreditCardVisaSwiped() {
		CreditCard creditCard = getCreditCardVisa();
		creditCard.setNumber(null);
		((Identifiable)creditCard).setUuid(CreditCardConstants.UUID);
		creditCard.setNumberMasked(CreditCardConstants.NUMBER_VISA_MASKED);
		creditCard.setToken(CreditCardConstants.CREDIT_CARD_VISA_SWIPED_TOKEN);
		creditCard.setEnabled(true);
		creditCard.setSwiped(true);
		creditCard.setTrack2(CreditCardConstants.TRACK2_VISA);
		return creditCard;
	}

	public CreditCard getCreditCardSavedVisaSwiped() {
		CreditCard creditCard = getCreditCardVisa();
		creditCard.setNumber(null);
		creditCard.setTrack2(null);
		((Identifiable)creditCard).setUuid(CreditCardConstants.UUID);
		creditCard.setNumberMasked(CreditCardConstants.NUMBER_VISA_MASKED);
		creditCard.setToken(CreditCardConstants.CREDIT_CARD_VISA_SWIPED_TOKEN);
		creditCard.setEnabled(true);
		creditCard.setSwiped(true);
		return creditCard;
	}

}
