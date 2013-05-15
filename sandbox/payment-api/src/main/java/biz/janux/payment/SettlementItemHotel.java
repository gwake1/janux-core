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

import java.math.BigDecimal;
import java.util.Date;

/**
 * This settlement item is specific for {@link MerchantAccount} of type {@link IndustryType#HOTEL}
 * @author albertobuffagni@gmail.com
 *
 */
public interface SettlementItemHotel extends SettlementItem {
	
	/**
	 * This 12-character numeric field contains the daily room rate associated with the settlement 
	 * transaction. The field entry must be right-justified and zero-filled. This field must contain at 
	 * least one non-zero value.
	 */
	public BigDecimal getAverageRate();
	public void setAverageRate(BigDecimal averageRate);
	
	/**
	 * For Hotel category merchants, this field must contain the Checkout Date.
	 * 
	 */
	public Date getCheckOutDate();
	public void setCheckOutDate(Date checkOutDate);
	
}
