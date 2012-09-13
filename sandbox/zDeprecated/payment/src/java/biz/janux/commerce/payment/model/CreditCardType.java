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
/**
 * 
 * @author Nilesh
 * 
 * This Model is mapped to creditcardtype table 
 *
 */
public class CreditCardType {
	
	private long creditCardTypeId;
	
	private String title;
	
	private String code;
	/**
	 * 
	 * @return long
	 */
	public long getCreditCardTypeId() {
		return creditCardTypeId;
	}
	/**
	 * 
	 * @param creditCardTypeId
	 */
	public void setCreditCardTypeId(long creditCardTypeId) {
		this.creditCardTypeId = creditCardTypeId;
	}
	/**
	 * 
	 * @return String
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
