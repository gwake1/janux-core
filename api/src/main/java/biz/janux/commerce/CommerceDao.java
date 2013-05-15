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

import org.janux.bus.persistence.DataAccessObject;

import biz.janux.commerce.Currency;
import biz.janux.commerce.DepositMethod;
import biz.janux.commerce.GuaranteeMethod;
import biz.janux.commerce.PaymentMethod;

public interface CommerceDao extends DataAccessObject
{
	Currency findCurrencyByCode(final String code);
	
	PaymentMethod findPaymentMethodByCode(final String code);
	
	GuaranteeMethod findGuaranteeMethodByCode(final String code);
	
	DepositMethod findDepositMethodByCode(final String code);
	
	List<Currency> getCurrencies();
	
	List<PaymentMethod> getPaymentMethods();

	List<GuaranteeMethod> getGuaranteeMethods();
	
	List<DepositMethod> getDepositMethods();
}
