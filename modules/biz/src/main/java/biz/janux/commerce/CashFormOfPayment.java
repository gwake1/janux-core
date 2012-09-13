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

/*
 * Created on Mar 17, 2006
 */
package biz.janux.commerce;

/**
  * This class identifies a cash form of payment
 * @author David Fairchild
 *
 */
public class CashFormOfPayment extends AbstractFormOfPayment
{
	private static final long serialVersionUID = -5575498897810243136L;
	/** the cash currency */
    private String currencyCode;
    
	/**
	 * Constructor
	 */
	public CashFormOfPayment()
	{
	}

	/**
	 * @return Returns the currencyCode.
	 */
	public String getCurrencyCode()
	{
		return currencyCode;
	}
	/**
	 * @param currencyCode The currencyCode to set.
	 */
	public void setCurrencyCode(String currencyCode)
	{
		this.currencyCode = currencyCode;
	}
	
	public Object clone() 
	{
        Object result = super.clone();
        return result;
	}
	
}
