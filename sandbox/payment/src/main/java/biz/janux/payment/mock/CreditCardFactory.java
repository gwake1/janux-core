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

import biz.janux.geography.PostalAddress;
import biz.janux.payment.CreditCard;

/**
 ***************************************************************************************************
 * Convenience factory interface for creating mock credit card data
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since
 ***************************************************************************************************
 */
public interface CreditCardFactory {

	public CreditCard getCreditCardVisa();
	
	public CreditCard getCreditCardVisaSwiped();
	
	public CreditCard getCreditCardSavedVisaSwiped();
	
	public CreditCard getCreditCardSavedVisa();
	
	public CreditCard getCreditCardAmericanExpress();
	
	public CreditCard getCreditCardCompleteVisa();

	/**
	 * 
	 * @param holderName
	 * @param number
	 * @param expirationDate
	 * @param typeCode
	 * @param postalAddress
	 * @return
	 */
	public CreditCard getCreditCard(String holderName, String number, Date expirationDate, String typeCode, PostalAddress postalAddress);
	
}
