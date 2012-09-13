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

package biz.janux.commerce.payment.Address;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import biz.janux.commerce.payment.client.VitalMerchantAccount;
import biz.janux.commerce.payment.implementation.common.dao.hibernate.HibernateDao;
import biz.janux.commerce.payment.implementation.vendor.vital.TransactionServiceImpl;
import biz.janux.commerce.payment.implementation.vendor.vital.transaction.VitalAddressVerification;
import biz.janux.commerce.payment.interfaces.response.AddressVerificationResponse;
import biz.janux.commerce.payment.model.AddressVerificationModel;
import biz.janux.commerce.payment.model.CreditCard;
import junit.framework.TestCase;
/**
 * 
 * @author Nilesh
 * 
 *  This is a test case class,
 *  used for  Address verification
 *
 */
public class TestCaseAddress extends TestCase{

	
  public void testAddressVerification(){
	  
 	 AddressVerificationModel addressVerification = new AddressVerificationModel();
	 VitalMerchantAccount merchant = new VitalMerchantAccount();
	 CreditCard creditCard = new CreditCard();
	
	 /**
	  *  use of hibernate and open the  session
	  */
	 HibernateDao hibernateDao = HibernateDao.getInstance();
	 
	 /**
	  * access database using hibernate  
	  */
	 VitalMerchantAccount loadMerchent= (VitalMerchantAccount)hibernateDao.loadfromDB(VitalMerchantAccount.class,33L);
	 CreditCard loadCreditCard = (CreditCard)hibernateDao.loadfromDB(CreditCard.class,895L);
	
	  /**
	   * Set data field using hibernet
	   * 
	   */
	 addressVerification.setInstrumentId(loadCreditCard.getCreditCardId());
	 addressVerification.setMerchantId(loadMerchent.getId());
	 VitalAddressVerification vitalAddVerification = new VitalAddressVerification(addressVerification); 
	 TransactionServiceImpl transactionServiceImpl=new TransactionServiceImpl();
	 AddressVerificationResponse addressVerificationResponse = transactionServiceImpl.process(addressVerification); 
	 
	
	
	      assertNotNull(addressVerificationResponse);
 	      assertFalse(addressVerificationResponse.isNoMatch());
 	      assertTrue(addressVerificationResponse.isExactMatch());
//		  assertTrue(addressVerificationResponse.isAddressMatch());
		}
	}
