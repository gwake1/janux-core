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
 * Industry type identify the industry type of the merchant 
 * submitting the settlement batch.  
 * This field should be configurable as a parameter.
 * @author albertobuffagni@gmail.com
 *
 */
public enum IndustryType {
	HOTEL,
	/**
	 * for now only is supported the hotel industry type 
	 */
	/*
	UNKNOWN_OR_UNSURE,
	AUTO_RENTAL,
	BANK_FINANCIAL_INSTITUTION,
	DIRECT_MARKETING,
	FOOD_RESTAURANT,
	GROCERY_STOR_SUPERMARKET,
	LIMITED_AMOUNT_TERMINAL,
	OIL_COMPANY_AUTOMATED_FUELING_SYSTEM,
	RETAIL
	*/

}
