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
import junit.framework.Assert;

import org.janux.bus.search.SearchCriteria;

import org.junit.After;
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
public class CreditCardService2Test {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("CreditCardStorageService")
	CreditCardStorageService<SearchCriteria> creditCardService;
	
	@Autowired
	@Qualifier("CreditCardTypeService")
	CreditCardTypeService<SearchCriteria> cardTypeService;
	
	@Autowired
	CreditCardFactory creditCardFactory;

	private String uuid;
	private CreditCard creditCard;

	@After
	public void clean()
	{
		try
		{ 
			this.creditCard = null;
			this.creditCardService.deleteOrDisable(uuid);
		}
		catch (RuntimeException e) { }
	}
	
	
	@Test
	public void testEncrypt() throws Exception
	{
		this.creditCard = this.creditCardService.saveOrUpdate(
			this.creditCardFactory.getCreditCardAmericanExpress());
				
		Assert.assertNotNull(this.creditCard);

		this.uuid = this.creditCard.getUuid();
		Assert.assertNotNull(this.uuid);

		assertEquals(CreditCardConstants.CREDIT_CARD_AMERICAN_EXPRESS_TOKEN, creditCard.getToken());
		assertEquals(CreditCardConstants.CARD_HOLDER_NAME, creditCard.getCardholderName());

		Assert.assertNotNull(creditCard.getBillingAddress());
		assertEquals(CreditCardConstants.CITY,           creditCard.getBillingAddress().getCityAsString());
		assertEquals(CreditCardConstants.COUNTRY_STRING, creditCard.getBillingAddress().getCountryAsString());
		assertEquals(CreditCardConstants.STATE_STRING,   creditCard.getBillingAddress().getStateProvinceAsString());
		assertEquals(CreditCardConstants.POSTAL_CODE,    creditCard.getBillingAddress().getPostalCode());
		assertEquals(CreditCardConstants.ADDRESS,        creditCard.getBillingAddress().getLine1());
		assertEquals(true, creditCard.isEnabled());

		Assert.assertNull(creditCard.getNumber());
		
		/*
		creditCard = this.creditCardService.load(uuid,true);
		
		Assert.assertNotNull(creditCard);
		Assert.assertEquals(CreditCardConstants.NUMBER_AMERICAN_EXPRESS,creditCard.getNumber());
		*/
	}


	private void loadCreditCard(String uuid) {
		CreditCard creditCard = this.creditCardService.load(uuid,false);
		
		Assert.assertNotNull(creditCard);
		assertEquals(CreditCardConstants.CREDIT_CARD_AMERICAN_EXPRESS_TOKEN, creditCard.getToken());
		assertEquals(CreditCardConstants.CARD_HOLDER_NAME, creditCard.getCardholderName());
		assertEquals(CreditCardConstants.CITY,             creditCard.getBillingAddress().getCityAsString());
		assertEquals(CreditCardConstants.COUNTRY_STRING,   creditCard.getBillingAddress().getCountryAsString());
		assertEquals(CreditCardConstants.STATE_STRING,     creditCard.getBillingAddress().getStateProvinceAsString());
		assertEquals(CreditCardConstants.POSTAL_CODE,      creditCard.getBillingAddress().getPostalCode());
		assertEquals(CreditCardConstants.ADDRESS,          creditCard.getBillingAddress().getLine1());
		assertEquals(true,creditCard.isEnabled());
		Assert.assertNull(creditCard.getNumber());
		
		creditCard = this.creditCardService.load(uuid,true);
		
		Assert.assertNotNull(creditCard);
		Assert.assertEquals(CreditCardConstants.NUMBER_AMERICAN_EXPRESS,creditCard.getNumber());
	}

}
