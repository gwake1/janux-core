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

package biz.janux.payment.gateway.controllers.creditcard;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.ui.ModelMap;

import biz.janux.payment.CreditCardImpl;
import biz.janux.payment.CreditCardStorageService;
import biz.janux.payment.mock.CreditCardFactory;

/**
 ***************************************************************************************************
 *
 *
 * @author <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since
 ***************************************************************************************************
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CreditCardControllerRestTest 
{
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CreditCardControllerRest controller;

	@Autowired
	@Qualifier("CreditCardStorageServiceMock")
	CreditCardStorageService creditCardService;

	@Autowired
	CreditCardFactory creditCardFactory;

	private String uuid;
	private CreditCardImpl creditCard;
	private ModelMap model = new ModelMap();

  @After
  public void clean()
  {
    try
    {
      this.creditCard = null;
      this.creditCardService.deleteOrDisable(this.uuid);
			this.uuid  = null;
			this.model = null;
    }
    catch (RuntimeException e) { }
  }
	
	@Test
	public void testSaveCreditCard() {
		this.controller.saveCreditCard(null, this.creditCardFactory.getCreditCardVisa(), null, false, this.model);
		this.creditCard = (CreditCardImpl)this.model.get("creditCard");
		this.uuid = this.creditCard.getUuid();
		log.debug("credit card after saveCreditCard test: {}", creditCard);
	}
}

