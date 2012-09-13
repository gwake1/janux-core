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
import java.util.List;

import org.janux.bus.persistence.Persistent;

import biz.janux.payment.CreditCard;

/**
 * Represents a Credit Card Authorization. 
 * 
 * @see Transaction
 *
 * @author Nilesh
 * @author albertobuffagni@gmail.com 
 */
public interface Authorization extends Transaction<AuthorizationResponse> {

	public CreditCard getCreditCard();
	public void setCreditCard(CreditCard creditCard);
	
	/**
	 * Sum of the auth amounts of each {@link AuthorizationModification} 
	 */
	public BigDecimal getAmount();
	public void setAmount(BigDecimal amount);

	/**
	 * Represents each authorization request.
	 * 
	 * If the {@link Authorization} was incremented or reverted, 
	 * it will have more than one instance of {@link AuthorizationModification}
	 */
	public List<AuthorizationModification> getModifications();
	public void setModifications(List<AuthorizationModification> modifications);
	
	/**
	 * If true indicates that the authorization was captured.
	 */
	public boolean isBatched();
	public void setBatched(boolean batched);
	
	/**
	 * Used to filter the authorizations done by a specific external entity such as Folios from the external app such as PMS, CRS, etc.
	 * This property will be used to find the authorizations when the payments are posted. So the payments will be assigned to some of these authorizations filtered.
	 * For ex: <external app>_<entity name>_<id> pms_folio_1234
	 */
	public String getExternalSourceId();
	public void setExternalSourceId(String externalSourceId);
	
}
