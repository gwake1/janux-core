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

import org.janux.bus.persistence.DataAccessHibImplAbstract;

import biz.janux.geography.CountryDaoHibImplGeneric;

/**
 * 
 * @deprecated use {@link CountryDaoHibImplGeneric}
 */
public class CommerceDaoHibImpl extends DataAccessHibImplAbstract implements CommerceDao
{
	
	@SuppressWarnings("unchecked")
	public Currency findCurrencyByCode(final String code)
	{
		final List<Currency> list = getHibernateTemplate().find("from CurrencyImpl where code=?", code);

		if (list.size() == 0)
		{
			throw new IllegalStateException("Currency code " + code + " not found");
		}
		else if (list.size() > 1)
		{
			throw new IllegalStateException("More than one entry with currency code " + code + " returned");
		}
		
		// return the single instance
		return ( list.get(0));
	}
	
	
	@SuppressWarnings("unchecked")
	public PaymentMethod findPaymentMethodByCode(final String code)
	{
		final List<PaymentMethod> list = getHibernateTemplate().find("from PaymentMethodImpl where code=?", code);

		if (list.size() == 0)
		{
			throw new IllegalStateException("Payment method code " + code + " not found");
		}
		else if (list.size() > 1)
		{
			throw new IllegalStateException("More than one entry for payment method code " + code + " returned");
		}
		
		// return the single instance
		return ( list.get(0));
	}
	
	
	@SuppressWarnings("unchecked")
	public GuaranteeMethod findGuaranteeMethodByCode(final String code)
	{
		final List<GuaranteeMethod> list = getHibernateTemplate().find("from GuaranteeMethodImpl where code=?", code);

		if (list.size() == 0)
		{
			throw new IllegalStateException("Guarantee method code " + code + " not found");
		}
		else if (list.size() > 1)
		{
			throw new IllegalStateException("More than one entry for guarantee method code " + code + " returned");
		}
		
		// return the single instance
		return ( list.get(0));
	}
	
	
	@SuppressWarnings("unchecked")
	public DepositMethod findDepositMethodByCode(final String code)
	{
		final List<DepositMethod> list = getHibernateTemplate().find("from DepositMethodImpl where code=?", code);

		if (list.size() == 0)
		{
			throw new IllegalStateException("Deposit method code " + code + " not found");
		}
		else if (list.size() > 1)
		{
			throw new IllegalStateException("More than one entry for deposit method code " + code + " returned");
		}
		
		// return the single instance
		return ( list.get(0));
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Currency> getCurrencies()
	{
		final List<Currency> list = getHibernateTemplate().find("from CurrencyImpl");
		return (list);
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentMethod> getPaymentMethods()
	{
		final List<PaymentMethod> list = getHibernateTemplate().find("from PaymentMethodImpl");
		return (list);
	}

	@SuppressWarnings("unchecked")
	public List<GuaranteeMethod> getGuaranteeMethods()
	{
		final List<GuaranteeMethod> list = getHibernateTemplate().find("from GuaranteeMethodImpl");
		return (list);
	}
	
	@SuppressWarnings("unchecked")
	public List<DepositMethod> getDepositMethods()
	{
		final List<DepositMethod> list = getHibernateTemplate().find("from DepositMethodImpl");
		return (list);
	}
	
}
