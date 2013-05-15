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

package biz.janux.commerce.payment.interfaces.model;
/**
 * 
 * @author Nilesh
 * </br>
 * Interface for Address
 *
 */
public interface Address {
	/**
	 * 
	 * @return String
	 */
	public String getAddressLine1();
	/**
	 * 
	 * @param addressLine1
	 */
	public void setAddressLine1(String addressLine1);
	/**
	 * 
	 * @return String
	 */
	public String getAddressLine2();
	/**
	 * 
	 * @param addressLine2
	 */
	public void setAddressLine2(String addressLine2);
	/**
	 * 
	 * @return String
	 */
	public String getCity();
	/**
	 * 
	 * @param city
	 */
	public void setCity(String city);
	/**
	 * 
	 * @return String
	 */
	public String getState();
	/**
	 * 
	 * @param state
	 */
	public void setState(String state);
	/**
	 * 
	 * @return String
	 */
	public String getCountry();
	/**
	 * 
	 * @param country
	 */
	public void setCountry(String country);
	/**
	 * 
	 * @return String
	 */
	public String getZipCode();
	/**
	 * 
	 * @param zipCode
	 */
	public void setZipCode(String zipCode);
}
