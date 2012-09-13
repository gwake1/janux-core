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

package biz.janux.commerce.payment.Authorization;

import java.math.BigDecimal;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate.encryptor.*;
import biz.janux.commerce.payment.client.VitalMerchantAccount;
import biz.janux.commerce.payment.implementation.common.dao.hibernate.HibernateDao;
import biz.janux.commerce.payment.implementation.vendor.vital.TransactionServiceImpl;
import biz.janux.commerce.payment.implementation.vendor.vital.transaction.VitalAuthorization;
import biz.janux.commerce.payment.interfaces.response.AuthorizationResponse;
import biz.janux.commerce.payment.model.Authorizationmodel;
import biz.janux.commerce.payment.model.CreditCard;

public class TestCaseAuthorization extends TestCase{

	public void testVitalAuthorization(){
		
        
		Authorizationmodel authorization = new Authorizationmodel();
		HibernateDao hibernateDao = HibernateDao.getInstance();
		
		VitalMerchantAccount loadMerchent= (VitalMerchantAccount)hibernateDao.loadfromDB(VitalMerchantAccount.class,34L);
		//VitalMerchantAccount loadMerchent= (VitalMerchantAccount)session.load(VitalMerchantAccount.class,104L);
		CreditCard loadCreditCard = (CreditCard)hibernateDao.loadfromDB(CreditCard.class,896L);
		//CreditCard loadCreditCard = (CreditCard)session.load(CreditCard.class,506114L);
		
		//loadCreditCard.encryptCardNumber();
//		System.out.println("cc number ::"+loadCreditCard.getCardNumber());
		
		authorization.setInstrumentId(loadCreditCard.getCreditCardId());
		authorization.setMerchantId(loadMerchent.getId());
		
		authorization.setAuthAmount(new BigDecimal(125.0));
//		System.out.println("curr ...."+loadMerchent.getCurrencySymbol("GBP"));
//		VitalAuthorization va = new VitalAuthorization(authorization);		
		TransactionServiceImpl tsi=new TransactionServiceImpl();
		AuthorizationResponse authorizationResponse = tsi.process(authorization); 		
	
		assertNotNull(authorizationResponse);
		assertTrue(authorizationResponse.isApproved());
		assertFalse(authorizationResponse.isDeclined());
	}
}
