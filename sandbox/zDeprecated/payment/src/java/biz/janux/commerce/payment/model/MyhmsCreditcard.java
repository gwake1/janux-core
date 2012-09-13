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

package biz.janux.commerce.payment.model;

import biz.janux.commerce.payment.util.CreditCardUtil;

public class MyhmsCreditcard {
	
	private long id;
	private long ccid;
	private long guestid;
	private String creditcardnumber;
	
	CreditCardUtil ccu =new CreditCardUtil();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCcid() {
		return ccid;
	}
	public void setCcid(long ccid) {
		this.ccid = ccid;
	}
	public long getGuestid() {
		return guestid;
	}
	public void setGuestid(long guestid) {
		this.guestid = guestid;
	}
	public String getCreditcardnumber() {
		
		return creditcardnumber;
	}
	public void setCreditcardnumber(String creditcardnumber) {
		String	ccnum= ccu.getMaskedNumber(creditcardnumber);
		
		this.creditcardnumber =creditcardnumber;
	}
}
