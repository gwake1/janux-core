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

import java.io.Serializable;

/**
 ***************************************************************************************************
 * Indicate the number of the batch used in the settlement process. 
 * This number will be incremented for each batch sent.
 * 
 * @author  <a href="mailto:alberto.buffagni@janux.org">Alberto Buffagni</a>
 *
 * @since 0.4.0 - 2011-11-15
 ***************************************************************************************************
 */
public interface BatchNumber extends Serializable {
	public int getNumber();
	public void setNumber(int number);

	public MerchantAccount getMerchantAccount();
	public void setMerchantAccount(MerchantAccount merchantAccount);
}
