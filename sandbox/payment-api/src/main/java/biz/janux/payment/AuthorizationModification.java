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
import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents each authorization request.
 * 
 * If the {@link Authorization} was incremented or reverted, it will have more than one instance of {@link AuthorizationModification}
 *    
 * @author albertobuffagni@gmail.com
 *
 */

public interface AuthorizationModification extends Serializable {

	/**       
	 * A random alphanumeric string that uniquely identifies this Credit Card in the Janux Payment
	 * Service. This is the code that external clients must use to reference the Credit Card when
	 * calling the Janux Payment Service.
	 */
	public String getUuid();
	
	public Date getDate();
	public void setDate(Date date);
	
	/**
	 * The sign of this field will indicate if the modifications is a increment or reversal.
	 */
	public BigDecimal getAmount();
	public void setAmount(BigDecimal amount);
	
}
