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

package biz.janux.payment;


/**
 * Represents a Credit Card Authorization for Hotels.
 * 
 * @author albertobuffagni@gmail.com 
 */
public interface AuthorizationHotel extends Authorization {

	/**
	 * The value of this sub-field is the anticipated length of the hotel stay or auto rental.
	 * When the market specific data is supplied in an incremental transaction, this sub-field 
	 * represents the number of additional days for the hotel stay or auto rental.
	 * This sub-field must be in the range of "01" to "99" for all original authorization requests. For
	 * incremental authorization requests, the range for this sub-field is "00" to "99". For No Show
	 * Authorizations, this sub-field should be set to "01". For Advanced Lodging Deposits and 
	 * Auto Rental PrePays, this sub-field should reflect the number of days being paid for in the 
	 * advanced payment.
	 */
	public Integer getStayDuration();
	public void setStayDuration(Integer stayDuration);	
}
