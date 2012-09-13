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

import org.janux.bus.persistence.HibernateDataAccessObject;

import biz.janux.commerce.CreditCardFop;

/**
 * Used to search for credit cards. This DAO may eventually be deprecated or
 * modified when we build a Credit Card vault.  In that implementations, we will
 * store on the Party side a token used to identify the credit card, and the
 * credit card will subsequently be retrieved from the vault
 *
 * @author <a href="mailto:alberto.buffagni@janux.org">Alberto Buffagni</a>
 * @author <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4
 */
public interface CreditCardFopDao  extends HibernateDataAccessObject 
{
	public List<CreditCardFop> findAll(CreditCardFopCriteria creditCardFopCriteria) throws Exception;
}
