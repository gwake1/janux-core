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


public interface AuthorizationHotelRemotingService extends RemotingService  {

	/**
	 * Authorizations are not batched and not disabled
	 */
	public List<AuthorizationHotel> findAuthorizationsActive(String businessUnitUuid, String creditCardUuid);

	/**
	 * Check if exist some authorization without take into account if it is disabled or batched.
	 */
	public boolean isAuthorizationExist(String businessUnitUuid, String creditCardUuid);	
	
	/**
	 * Save a new authorization if it is approved.
	 * Calculates the authorization amount and creates the {@link AuthorizationModification}
	 * If the authorization is {@link AuthorizationType#INCREMENTAL}
	 * the @param authorization is a existing authorization and 
	 * a new {@link AuthorizationModification} will be added with the auth amount.
	 */
	public AuthorizationHotel saveNew(AuthorizationHotel authorization, String businessUnitUuid, String creditCardUuid,AuthorizationType authorizationType);

}
