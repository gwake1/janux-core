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

package biz.janux.commerce;

import java.util.List;

import biz.janux.commerce.CreditCardFop;

/**
 * Preliminary Service for managing credit cards. When we build a standalone Credit Card Vault, this
 * service may be substantially modified, or moved to the Credit Card vault project.
 *
 * @author <a href="mailto:alberto.buffagni@janux.org">Alberto Buffagni</a>
 * @author <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4
 */
public interface CreditCardFopService 
{
	public void saveOrUpdate(CreditCardFop creditCardFop) throws CreditCardFopException;

	/** 
	 * Obscures the string provided, presumably a credit card number, for example
	 * by replacing all but the first and last 4 characters with asterisks
	 */
	public String maskNumber(String num);
	
	public List<CreditCardFop> findByCriteria(CreditCardFopCriteria creditCardFopCriteria)throws CreditCardFopException;

	/**
	 * Decrypts the credit card number, and, if the 'toShow' flag is true, makes the unencrypted
	 * credit card number visible; this method should be used with caution, as only accounts with
	 * proper permissions should be able to view the credit card in clear text.  if the 'toShow' flag
	 * is false, it is assumed that the credit card number is decrypted so that it can be masked.
	 *
	 * @param cc
	 * @param toShow if it is true, the new value is set on cardNumberToShow attribute of the {@link CreditCardFop}
	 * @throws CreditCardFopException
	 */
	public void decryptCreditCardNumber(CreditCardFop cc, boolean toShow) throws CreditCardFopException;
	
	/**
	 * Masks the credit card number, and, if the 'toShow' flag is true, makes this masked credit
	 * card number visible.  If the 'toShow' flag is false, it is assumed that the credit card number
	 * is no longer needed for transactions, and it is being stored in masked fashion for reference
	 *
	 * @param cc
	 * @param toShow if it is true, the new value is set on cardNumberToShow attribute of the {@link CreditCardFop}
	 * @throws CreditCardFopException
	 */
	public void maskCreditCardNumber(CreditCardFop cc, boolean toShow) throws CreditCardFopException;
	
	/**
	 * Encrypts the cc number
	 *
	 * @param cc
	 * @param toShow if it is true, the new value is set on cardNumberToShow attribute of the {@link CreditCardFop}
	 * @throws CreditCardFopException
	 */
	public void encryptCreditCardNumber(CreditCardFop cc, boolean toShow) throws CreditCardFopException;


	/**
	 * This checks if the card number has to be masked or decrypted
	 *
	 * @param cc
	 * @param displayCreditCardMasked
	 * @throws CreditCardManagmentException
	 */
	public void prepareCreditCardToShow(CreditCardFop cc, boolean displayCreditCardMasked) throws CreditCardFopException;

}
