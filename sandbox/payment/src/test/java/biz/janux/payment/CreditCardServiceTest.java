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

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import junit.framework.Assert;

import org.janux.bus.search.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.trg.search.Search;

import biz.janux.payment.mock.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CreditCardServiceTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("CreditCardStorageService")
	CreditCardStorageService<SearchCriteria> creditCardService;
	
	@Autowired
	@Qualifier("CreditCardTypeService")
	CreditCardTypeService<SearchCriteria> cardTypeService;
	
	@Autowired
	CreditCardFactory creditCardFactory;
	
	@Autowired
	@Qualifier("BusinessUnitService")
	private BusinessUnitService<SearchCriteria> businessUnitService;
	
	@Test
	public void crudCreditCard() throws Exception{
		MDC.put("context", "testing");
		String uuid=null; 
		try {
			uuid = createCreditCard();
			loadCreditCard(uuid);
			findCreditCard(uuid);
			deleteCreditCard(uuid);
			findDeletedCreditCard(uuid);
		} catch (RuntimeException e) {
			throw e;
		}
		finally{
			/**
			 * clean the database
			 */
			try {
				getCreditCardService().deleteOrDisable(uuid);
			} catch (RuntimeException e) {
				
			}
		}
	}
	
	private void loadCreditCard(String uuid) {
		CreditCard creditCard = getCreditCardService().load(uuid,false);
		
		Assert.assertNotNull(creditCard);
		assertEquals(CreditCardConstants.CREDIT_CARD_AMERICAN_EXPRESS_TOKEN,creditCard.getToken());
		assertEquals(CreditCardConstants.CARD_HOLDER_NAME,creditCard.getCardholderName());
		assertEquals(CreditCardConstants.CITY,creditCard.getBillingAddress().getCityAsString());
		assertEquals(CreditCardConstants.COUNTRY_STRING,creditCard.getBillingAddress().getCountryAsString());
		assertEquals(CreditCardConstants.STATE_STRING,creditCard.getBillingAddress().getStateProvinceAsString());
		assertEquals(CreditCardConstants.POSTAL_CODE,creditCard.getBillingAddress().getPostalCode());
		assertEquals(CreditCardConstants.ADDRESS,creditCard.getBillingAddress().getLine1());
		assertEquals(true,creditCard.isEnabled());
		Assert.assertNull(creditCard.getNumber());
		
		creditCard = getCreditCardService().load(uuid,true);
		
		Assert.assertNotNull(creditCard);
		Assert.assertEquals(CreditCardConstants.NUMBER_AMERICAN_EXPRESS,creditCard.getNumber());
		
	}

	public String createCreditCard() throws Exception{
		CreditCard creditCard = getCreditCardFactory().getCreditCardAmericanExpress();
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();
		return uuid;
	}

	public void findCreditCard(String uuid) throws Exception{
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("uuid",uuid);
		
		CreditCard creditCard = getCreditCardService().findFirstByCriteria(searchCriteria);
		
		Assert.assertNotNull(creditCard);
		assertEquals(CreditCardConstants.CREDIT_CARD_AMERICAN_EXPRESS_TOKEN,creditCard.getToken());
		assertEquals(CreditCardConstants.CARD_HOLDER_NAME,creditCard.getCardholderName());
		assertEquals(CreditCardConstants.CITY,creditCard.getBillingAddress().getCityAsString());
		assertEquals(CreditCardConstants.COUNTRY_STRING,creditCard.getBillingAddress().getCountryAsString());
		assertEquals(CreditCardConstants.STATE_STRING,creditCard.getBillingAddress().getStateProvinceAsString());
		assertEquals(CreditCardConstants.POSTAL_CODE,creditCard.getBillingAddress().getPostalCode());
		assertEquals(CreditCardConstants.ADDRESS,creditCard.getBillingAddress().getLine1());
		assertEquals(false,creditCard.isSwiped());
		assertEquals(getBusinessUnitService().BUSINESS_UNIT_CODE_BY_DEFAULT,((CreditCardImpl)creditCard).getBusinessUnit().getCode());
		assertEquals(true,creditCard.isEnabled());
		Assert.assertNull(creditCard.getNumber());
		
		creditCard = getCreditCardService().findFirstByCriteria(searchCriteria,true);
		
		Assert.assertNotNull(creditCard);
		assertEquals(CreditCardConstants.CREDIT_CARD_AMERICAN_EXPRESS_TOKEN,creditCard.getToken());
		assertEquals(CreditCardConstants.CARD_HOLDER_NAME,creditCard.getCardholderName());
		assertEquals(CreditCardConstants.CITY,creditCard.getBillingAddress().getCityAsString());
		assertEquals(CreditCardConstants.COUNTRY_STRING,creditCard.getBillingAddress().getCountryAsString());
		assertEquals(CreditCardConstants.STATE_STRING,creditCard.getBillingAddress().getStateProvinceAsString());
		assertEquals(CreditCardConstants.POSTAL_CODE,creditCard.getBillingAddress().getPostalCode());
		assertEquals(CreditCardConstants.ADDRESS,creditCard.getBillingAddress().getLine1());
		assertEquals(getBusinessUnitService().BUSINESS_UNIT_CODE_BY_DEFAULT,((CreditCardImpl)creditCard).getBusinessUnit().getCode());
		assertEquals(true,creditCard.isEnabled());
		assertEquals(false,creditCard.isSwiped());
		Assert.assertEquals(CreditCardConstants.NUMBER_AMERICAN_EXPRESS,creditCard.getNumber());
	}

	public void deleteCreditCard(String uuid) throws Exception{
		getCreditCardService().deleteOrDisable(uuid);
	}
	

	public void findDeletedCreditCard(String uuid) throws Exception{
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("uuid",uuid);
		
		CreditCard creditCard = getCreditCardService().findFirstByCriteria(searchCriteria);
		Assert.assertNull(creditCard);	
	}

	@Test
	public void updateExistingCreditCardUsingBusinessUnitAndNumber() throws Exception{
		CreditCard creditCard = getCreditCardFactory().getCreditCardVisa();
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();

		creditCard = getCreditCardService().load(uuid, true);
		
		CreditCard creditCard1 = getCreditCardService().load(uuid, true);
		String newCity = "TheCityWasChanged";
		creditCard1.setSwiped(true);
		creditCard1.setTrack2(CreditCardConstants.TRACK2_VISA);
		creditCard1.getBillingAddress().setCityAsString(newCity);
		uuid = getCreditCardService().saveOrUpdate(creditCard1).getUuid();
		CreditCard creditCard2 = getCreditCardService().load(uuid, true);
		
		assertEquals(creditCard.getCardholderName(), creditCard2.getCardholderName());
		assertEquals(creditCard.getExpirationDate(), creditCard2.getExpirationDate());
		assertEquals(creditCard.getNumber(), creditCard2.getNumber());
		assertEquals(creditCard.getNumberMasked(), creditCard2.getNumberMasked());
		Assert.assertFalse(creditCard.getToken().equals(creditCard2.getToken()));
		assertEquals(creditCard.getType(), creditCard2.getType());
		assertEquals(((CreditCardImpl)creditCard).getNumberHash(), ((CreditCardImpl)creditCard2).getNumberHash());
		assertEquals(((CreditCardImpl)creditCard).getBusinessUnit(), ((CreditCardImpl)creditCard2).getBusinessUnit());
		assertEquals(creditCard.getBillingAddress().getCountryAsString(), creditCard2.getBillingAddress().getCountryAsString());
		assertEquals(creditCard.getBillingAddress().getStateProvinceAsString(), creditCard2.getBillingAddress().getStateProvinceAsString());
		assertEquals(creditCard.getBillingAddress().getLine1(), creditCard2.getBillingAddress().getLine1());
		assertEquals(creditCard.getBillingAddress().getPostalCode(), creditCard2.getBillingAddress().getPostalCode());
		assertEquals(creditCard.isEnabled(), creditCard2.isEnabled());
		assertEquals(!creditCard.isSwiped(), creditCard2.isSwiped());
		Assert.assertNotNull(creditCard2.getTrack2());
		assertEquals(newCity,creditCard2.getBillingAddress().getCityAsString());
		
		getCreditCardService().deleteOrDisable(uuid);
	}
	
	@Test
	public void updateExistingCreditCard() throws Exception{
		CreditCard creditCard = getCreditCardFactory().getCreditCardVisa();
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();
		
		creditCard = getCreditCardService().load(uuid, true);

		CreditCard creditCard1 = getCreditCardService().load(uuid, true);
		String newCity = "TheCityWasChanged";
		creditCard1.getBillingAddress().setCityAsString(newCity);
		creditCard1.setSwiped(true);
		creditCard1.setTrack2(CreditCardConstants.TRACK2_VISA);
		
		uuid = getCreditCardService().saveOrUpdate(creditCard1).getUuid();
		CreditCard creditCard2 = getCreditCardService().load(uuid, true);
		
		assertEquals(creditCard.getCardholderName(), creditCard2.getCardholderName());
		assertEquals(creditCard.getExpirationDate(), creditCard2.getExpirationDate());
		assertEquals(creditCard.getNumberMasked(), creditCard2.getNumberMasked());
		Assert.assertFalse(creditCard.getToken().equals(creditCard2.getToken()));
		assertEquals(creditCard.getType(), creditCard2.getType());
		assertEquals(((CreditCardImpl)creditCard).getNumberHash(), ((CreditCardImpl)creditCard2).getNumberHash());
		assertEquals(((CreditCardImpl)creditCard).getBusinessUnit(), ((CreditCardImpl)creditCard2).getBusinessUnit());
		assertEquals(creditCard.getBillingAddress().getCountryAsString(), creditCard2.getBillingAddress().getCountryAsString());
		assertEquals(creditCard.getBillingAddress().getStateProvinceAsString(), creditCard2.getBillingAddress().getStateProvinceAsString());
		assertEquals(creditCard.getBillingAddress().getLine1(), creditCard2.getBillingAddress().getLine1());
		assertEquals(creditCard.getBillingAddress().getPostalCode(), creditCard2.getBillingAddress().getPostalCode());
		assertEquals(creditCard.isEnabled(), creditCard2.isEnabled());
		assertEquals(!creditCard.isSwiped(), creditCard2.isSwiped());
		Assert.assertNotNull(creditCard2.getTrack2());
		assertEquals(newCity,creditCard2.getBillingAddress().getCityAsString());
		
		getCreditCardService().deleteOrDisable(uuid);
	}
	
	@Test
	public void updateExpirationDateCreditCard() throws Exception{
		CreditCard creditCard = getCreditCardFactory().getCreditCardVisa();
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();
		
		creditCard = getCreditCardService().load(uuid, true);

		CreditCard creditCard1 = getCreditCardService().load(uuid, true);
		String newCity = "TheCityWasChanged";
		creditCard1.getBillingAddress().setCityAsString(newCity);
		creditCard1.setSwiped(true);
		creditCard1.setTrack2(CreditCardConstants.TRACK2_VISA);
		Date currentDate = new Date();
		currentDate.setYear(currentDate.getYear()+1);
		creditCard1.setExpirationDate(currentDate);
		
		uuid = getCreditCardService().saveOrUpdate(creditCard1).getUuid();
		CreditCard creditCard2 = getCreditCardService().load(uuid, true);
		
		assertEquals(creditCard.getCardholderName(), creditCard2.getCardholderName());
		Assert.assertTrue(creditCard.getExpirationDate().before(creditCard2.getExpirationDate()));
		assertEquals(creditCard.getNumberMasked(), creditCard2.getNumberMasked());
		Assert.assertFalse(creditCard.getToken().equals(creditCard2.getToken()));
		assertEquals(creditCard.getType(), creditCard2.getType());
		assertEquals(((CreditCardImpl)creditCard).getNumberHash(), ((CreditCardImpl)creditCard2).getNumberHash());
		assertEquals(((CreditCardImpl)creditCard).getBusinessUnit(), ((CreditCardImpl)creditCard2).getBusinessUnit());
		assertEquals(creditCard.getBillingAddress().getCountryAsString(), creditCard2.getBillingAddress().getCountryAsString());
		assertEquals(creditCard.getBillingAddress().getStateProvinceAsString(), creditCard2.getBillingAddress().getStateProvinceAsString());
		assertEquals(creditCard.getBillingAddress().getLine1(), creditCard2.getBillingAddress().getLine1());
		assertEquals(creditCard.getBillingAddress().getPostalCode(), creditCard2.getBillingAddress().getPostalCode());
		assertEquals(creditCard.isEnabled(), creditCard2.isEnabled());
		assertEquals(!creditCard.isSwiped(), creditCard2.isSwiped());
		Assert.assertNotNull(creditCard2.getTrack2());
		assertEquals(newCity,creditCard2.getBillingAddress().getCityAsString());
		
		getCreditCardService().deleteOrDisable(uuid);
	}

	@Test
	public void crudSwipedCreditCard() throws Exception{
		MDC.put("context", "testing");
		String uuid=null; 
		try {
			uuid = createSwipedCreditCard();
			loadSwipedCreditCard(uuid);
			findSwipedCreditCard(uuid);
			deleteSwipedCreditCard(uuid);
			findDeletedSwipedCreditCard(uuid);
		} catch (RuntimeException e) {
			throw e;
		}
		finally{
			/**
			 * clean the database
			 */
			try {
				getCreditCardService().deleteOrDisable(uuid);
			} catch (RuntimeException e) {
				
			}
		}
	}
	
	private void loadSwipedCreditCard(String uuid) {
		CreditCard creditCard = getCreditCardService().load(uuid,false);
		
		Assert.assertNotNull(creditCard);
		assertEquals(CreditCardConstants.CREDIT_CARD_VISA_SWIPED_TOKEN,creditCard.getToken());
		assertEquals(CreditCardConstants.CARD_HOLDER_NAME,creditCard.getCardholderName());
		assertEquals(CreditCardConstants.CITY,creditCard.getBillingAddress().getCityAsString());
		assertEquals(CreditCardConstants.COUNTRY_STRING,creditCard.getBillingAddress().getCountryAsString());
		assertEquals(CreditCardConstants.STATE_STRING,creditCard.getBillingAddress().getStateProvinceAsString());
		assertEquals(CreditCardConstants.POSTAL_CODE,creditCard.getBillingAddress().getPostalCode());
		assertEquals(CreditCardConstants.ADDRESS,creditCard.getBillingAddress().getLine1());
		assertEquals(true,creditCard.isEnabled());
		assertEquals(true,creditCard.isSwiped());
		Assert.assertNull(creditCard.getTrack2());
		Assert.assertNull(creditCard.getNumber());
		
		creditCard = getCreditCardService().load(uuid,true);
		
		Assert.assertNotNull(creditCard);
		Assert.assertEquals(CreditCardConstants.NUMBER_VISA,creditCard.getNumber());
		Assert.assertEquals(CreditCardConstants.TRACK2_VISA,creditCard.getTrack2());
		
	}

	public String createSwipedCreditCard() throws Exception{
		CreditCard creditCard = getCreditCardFactory().getCreditCardVisaSwiped();
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();
		return uuid;
	}

	public void findSwipedCreditCard(String uuid) throws Exception{
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("uuid",uuid);
		
		CreditCard creditCard = getCreditCardService().findFirstByCriteria(searchCriteria);
		
		Assert.assertNotNull(creditCard);
		assertEquals(CreditCardConstants.CREDIT_CARD_VISA_SWIPED_TOKEN,creditCard.getToken());
		assertEquals(CreditCardConstants.CARD_HOLDER_NAME,creditCard.getCardholderName());
		assertEquals(CreditCardConstants.CITY,creditCard.getBillingAddress().getCityAsString());
		assertEquals(CreditCardConstants.COUNTRY_STRING,creditCard.getBillingAddress().getCountryAsString());
		assertEquals(CreditCardConstants.STATE_STRING,creditCard.getBillingAddress().getStateProvinceAsString());
		assertEquals(CreditCardConstants.POSTAL_CODE,creditCard.getBillingAddress().getPostalCode());
		assertEquals(CreditCardConstants.ADDRESS,creditCard.getBillingAddress().getLine1());
		assertEquals(getBusinessUnitService().BUSINESS_UNIT_CODE_BY_DEFAULT,((CreditCardImpl)creditCard).getBusinessUnit().getCode());
		assertEquals(true,creditCard.isEnabled());
		Assert.assertNull(creditCard.getNumber());
		assertEquals(true,creditCard.isSwiped());
		Assert.assertNull(creditCard.getTrack2());
		
		creditCard = getCreditCardService().findFirstByCriteria(searchCriteria,true);
		
		Assert.assertNotNull(creditCard);
		assertEquals(CreditCardConstants.CREDIT_CARD_VISA_SWIPED_TOKEN,creditCard.getToken());
		assertEquals(CreditCardConstants.CARD_HOLDER_NAME,creditCard.getCardholderName());
		assertEquals(CreditCardConstants.CITY,creditCard.getBillingAddress().getCityAsString());
		assertEquals(CreditCardConstants.COUNTRY_STRING,creditCard.getBillingAddress().getCountryAsString());
		assertEquals(CreditCardConstants.STATE_STRING,creditCard.getBillingAddress().getStateProvinceAsString());
		assertEquals(CreditCardConstants.POSTAL_CODE,creditCard.getBillingAddress().getPostalCode());
		assertEquals(CreditCardConstants.ADDRESS,creditCard.getBillingAddress().getLine1());
		assertEquals(getBusinessUnitService().BUSINESS_UNIT_CODE_BY_DEFAULT,((CreditCardImpl)creditCard).getBusinessUnit().getCode());
		assertEquals(true,creditCard.isEnabled());
		Assert.assertEquals(CreditCardConstants.NUMBER_VISA,creditCard.getNumber());
		assertEquals(true,creditCard.isSwiped());
		Assert.assertEquals(CreditCardConstants.TRACK2_VISA,creditCard.getTrack2());
	}

	public void deleteSwipedCreditCard(String uuid) throws Exception{
		getCreditCardService().deleteOrDisable(uuid);
	}
	

	public void findDeletedSwipedCreditCard(String uuid) throws Exception{
		Search searchCriteria = new Search();
		searchCriteria.addFilterEqual("uuid",uuid);
		
		CreditCard creditCard = getCreditCardService().findFirstByCriteria(searchCriteria);
		Assert.assertNull(creditCard);	
	}

	@Test
	public void updateExistingSwipedCreditCardUsingBusinessUnitAndNumber() throws Exception{
		CreditCard creditCard = getCreditCardFactory().getCreditCardVisaSwiped();
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();

		creditCard = getCreditCardService().load(uuid, true);
		
		CreditCard creditCard1 = getCreditCardService().load(uuid, true);
		String newCity = "TheCityWasChanged";
		creditCard1.setSwiped(false);
		creditCard1.getBillingAddress().setCityAsString(newCity);
		uuid = getCreditCardService().saveOrUpdate(creditCard1).getUuid();
		CreditCard creditCard2 = getCreditCardService().load(uuid, true);
		
		assertEquals(creditCard.getCardholderName(), creditCard2.getCardholderName());
		assertEquals(creditCard.getExpirationDate(), creditCard2.getExpirationDate());
		assertEquals(creditCard.getNumber(), creditCard2.getNumber());
		assertEquals(creditCard.getNumberMasked(), creditCard2.getNumberMasked());
		Assert.assertFalse(creditCard.getToken().equals(creditCard2.getToken()));
		assertEquals(creditCard.getType(), creditCard2.getType());
		assertEquals(((CreditCardImpl)creditCard).getNumberHash(), ((CreditCardImpl)creditCard2).getNumberHash());
		assertEquals(((CreditCardImpl)creditCard).getBusinessUnit(), ((CreditCardImpl)creditCard2).getBusinessUnit());
		assertEquals(creditCard.getBillingAddress().getCountryAsString(), creditCard2.getBillingAddress().getCountryAsString());
		assertEquals(creditCard.getBillingAddress().getStateProvinceAsString(), creditCard2.getBillingAddress().getStateProvinceAsString());
		assertEquals(creditCard.getBillingAddress().getLine1(), creditCard2.getBillingAddress().getLine1());
		assertEquals(creditCard.getBillingAddress().getPostalCode(), creditCard2.getBillingAddress().getPostalCode());
		assertEquals(creditCard.isEnabled(), creditCard2.isEnabled());
		Assert.assertFalse(creditCard.isSwiped()==creditCard2.isSwiped());
		assertEquals(newCity,creditCard2.getBillingAddress().getCityAsString());
		Assert.assertNull(creditCard2.getTrack2());
		getCreditCardService().deleteOrDisable(uuid);
	}
	
	@Test
	public void updateExistingSwipedCreditCard() throws Exception{
		CreditCard creditCard = getCreditCardFactory().getCreditCardVisaSwiped();
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();

		creditCard = getCreditCardService().load(uuid, true);

		CreditCard creditCard1 = getCreditCardService().load(uuid, true);
		String newCity = "TheCityWasChanged";
		creditCard1.getBillingAddress().setCityAsString(newCity);
		creditCard1.setSwiped(false);
		uuid = getCreditCardService().saveOrUpdate(creditCard1).getUuid();
		CreditCard creditCard2 = getCreditCardService().load(uuid, true);
		
		assertEquals(creditCard.getCardholderName(), creditCard2.getCardholderName());
		assertEquals(creditCard.getExpirationDate(), creditCard2.getExpirationDate());
		assertEquals(creditCard.getNumberMasked(), creditCard2.getNumberMasked());
		Assert.assertFalse(creditCard.getToken().equals(creditCard2.getToken()));
		assertEquals(creditCard.getType(), creditCard2.getType());
		assertEquals(((CreditCardImpl)creditCard).getNumberHash(), ((CreditCardImpl)creditCard2).getNumberHash());
		assertEquals(((CreditCardImpl)creditCard).getBusinessUnit(), ((CreditCardImpl)creditCard2).getBusinessUnit());
		assertEquals(creditCard.getBillingAddress().getCountryAsString(), creditCard2.getBillingAddress().getCountryAsString());
		assertEquals(creditCard.getBillingAddress().getStateProvinceAsString(), creditCard2.getBillingAddress().getStateProvinceAsString());
		assertEquals(creditCard.getBillingAddress().getLine1(), creditCard2.getBillingAddress().getLine1());
		assertEquals(creditCard.getBillingAddress().getPostalCode(), creditCard2.getBillingAddress().getPostalCode());
		assertEquals(creditCard.isEnabled(), creditCard2.isEnabled());
		Assert.assertFalse(creditCard.isSwiped()==creditCard2.isSwiped());
		Assert.assertNull(creditCard2.getTrack2());
		assertEquals(newCity,creditCard2.getBillingAddress().getCityAsString());
		
		getCreditCardService().deleteOrDisable(uuid);
	}
	
	@Test
	public void updateExpirationDateSwipedCreditCard() throws Exception{
		CreditCard creditCard = getCreditCardFactory().getCreditCardVisaSwiped();
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();

		creditCard = getCreditCardService().load(uuid, true);

		CreditCard creditCard1 = getCreditCardService().load(uuid, true);
		String newCity = "TheCityWasChanged";
		creditCard1.getBillingAddress().setCityAsString(newCity);
		creditCard1.setSwiped(false);
		Date currentDate = new Date();
		currentDate.setYear(currentDate.getYear()+1);
		creditCard1.setExpirationDate(currentDate);

		uuid = getCreditCardService().saveOrUpdate(creditCard1).getUuid();
		CreditCard creditCard2 = getCreditCardService().load(uuid, true);
		
		assertEquals(creditCard.getCardholderName(), creditCard2.getCardholderName());
		Assert.assertTrue(creditCard.getExpirationDate().before(creditCard2.getExpirationDate()));
		assertEquals(creditCard.getNumberMasked(), creditCard2.getNumberMasked());
		Assert.assertFalse(creditCard.getToken().equals(creditCard2.getToken()));
		assertEquals(creditCard.getType(), creditCard2.getType());
		assertEquals(((CreditCardImpl)creditCard).getNumberHash(), ((CreditCardImpl)creditCard2).getNumberHash());
		assertEquals(((CreditCardImpl)creditCard).getBusinessUnit(), ((CreditCardImpl)creditCard2).getBusinessUnit());
		assertEquals(creditCard.getBillingAddress().getCountryAsString(), creditCard2.getBillingAddress().getCountryAsString());
		assertEquals(creditCard.getBillingAddress().getStateProvinceAsString(), creditCard2.getBillingAddress().getStateProvinceAsString());
		assertEquals(creditCard.getBillingAddress().getLine1(), creditCard2.getBillingAddress().getLine1());
		assertEquals(creditCard.getBillingAddress().getPostalCode(), creditCard2.getBillingAddress().getPostalCode());
		assertEquals(creditCard.isEnabled(), creditCard2.isEnabled());
		assertEquals(creditCard.isSwiped(), !creditCard2.isSwiped());
		Assert.assertNull(creditCard2.getTrack2());
		assertEquals(newCity,creditCard2.getBillingAddress().getCityAsString());
		
		getCreditCardService().deleteOrDisable(uuid);
	}
	
	@Test
	public void updateExpirationDateBeforeSwipedCreditCard() throws Exception{
		Date currentDate = new Date();
		CreditCard creditCard = getCreditCardFactory().getCreditCardVisaSwiped();
		currentDate.setYear(currentDate.getYear()+2);
		creditCard.setExpirationDate(currentDate);
		String uuid = getCreditCardService().saveOrUpdate(creditCard).getUuid();

		creditCard = getCreditCardService().load(uuid, true);

		CreditCard creditCard1 = getCreditCardService().load(uuid, true);
		String newCity = "TheCityWasChanged";
		creditCard1.getBillingAddress().setCityAsString(newCity);
		creditCard1.setSwiped(false);
		currentDate.setYear(currentDate.getYear()-1);
		creditCard1.setExpirationDate(currentDate);

		uuid = getCreditCardService().saveOrUpdate(creditCard1).getUuid();
		CreditCard creditCard2 = getCreditCardService().load(uuid, true);
		
		assertEquals(creditCard.getCardholderName(), creditCard2.getCardholderName());
		Assert.assertFalse(creditCard.getExpirationDate().equals(creditCard2.getExpirationDate()));
		assertEquals(creditCard.getNumberMasked(), creditCard2.getNumberMasked());
		Assert.assertFalse(creditCard.getToken().equals(creditCard2.getToken()));
		assertEquals(creditCard.getType(), creditCard2.getType());
		assertEquals(((CreditCardImpl)creditCard).getNumberHash(), ((CreditCardImpl)creditCard2).getNumberHash());
		assertEquals(((CreditCardImpl)creditCard).getBusinessUnit(), ((CreditCardImpl)creditCard2).getBusinessUnit());
		assertEquals(creditCard.getBillingAddress().getCountryAsString(), creditCard2.getBillingAddress().getCountryAsString());
		assertEquals(creditCard.getBillingAddress().getStateProvinceAsString(), creditCard2.getBillingAddress().getStateProvinceAsString());
		assertEquals(creditCard.getBillingAddress().getLine1(), creditCard2.getBillingAddress().getLine1());
		assertEquals(creditCard.getBillingAddress().getPostalCode(), creditCard2.getBillingAddress().getPostalCode());
		assertEquals(creditCard.isEnabled(), creditCard2.isEnabled());
		Assert.assertFalse(creditCard.isSwiped()==creditCard2.isSwiped());
		Assert.assertNull(creditCard2.getTrack2());
		assertEquals(newCity,creditCard2.getBillingAddress().getCityAsString());
		
		getCreditCardService().deleteOrDisable(uuid);
	}
	
	
	
	public CreditCardStorageService<SearchCriteria> getCreditCardService() {
		return creditCardService;
	}

	public void setCreditCardService(CreditCardStorageService<SearchCriteria> creditCardService) {
		this.creditCardService = creditCardService;
	}

	public CreditCardTypeService<SearchCriteria> getCardTypeService() {
		return cardTypeService;
	}

	public void setCardTypeService(
			CreditCardTypeService<SearchCriteria> cardTypeService) {
		this.cardTypeService = cardTypeService;
	}

	public CreditCardFactory getCreditCardFactory() {
		return creditCardFactory;
	}

	public void setCreditCardFactory(CreditCardFactory creditCardFactory) {
		this.creditCardFactory = creditCardFactory;
	}

	public void setBusinessUnitService(BusinessUnitService<SearchCriteria> businessUnitService) {
		this.businessUnitService = businessUnitService;
	}

	public BusinessUnitService<SearchCriteria> getBusinessUnitService() {
		return businessUnitService;
	}

}


