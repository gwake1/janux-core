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

import org.janux.bus.search.SearchCriteria;

import biz.janux.payment.CreditCardType;

public interface AuthorizationHotelService <T extends AuthorizationHotel>{

	/**
	 * Save a new authorization if it is approved.
	 * Calculates the authorization amount and creates the {@link AuthorizationModification}
	 * If the authorization is {@link AuthorizationType#INCREMENTAL}
	 * the @param authorization is a existing authorization and 
	 * a new {@link AuthorizationModification} will be added with the auth amount.
	 * @param authorization
	 * @param authorizationType 
	 * @param authorizationResponse 
	 * @param authAmount 
	 */
	public T create(T authorization, BigDecimal authAmount, AuthorizationResponse authorizationResponse, AuthorizationType authorizationType);
	
	public List<T> findAll(SearchCriteria searchCriteria);
	
	public T find(SearchCriteria searchCriteria);
	
	public T load(T authorization);

	/**
	 * Check if the {@link CreditCardType} supports the {@link AuthorizationType} for do
	 * @param authorizationType
	 * @param creditCardType
	 */
    public boolean isAllowAuthorization(AuthorizationType authorizationType,CreditCardType creditCardType);

	/**
	 * A void is for a transaction that has been authorized, but not captured. 
	 * This is done before any funds have been actually transferred. 
	 * A void cancels the originally authorized transaction so it will not be captured.
	 * 
	 * If you do not perform a Void and you do not capture the authorized funds, 
	 * after a period of time the authorized transaction will be timed out by the processor, 
	 * and the hold on the funds will go away.
	 */
	public void voidAuthorization(T originalAuthorization);

	/**
	 * Set the authorization as batched. It indicates that the authorization was captured, settled
	 * @param authorization
	 */
	public void setBatched(T authorization);

	public T load(String authorizationUuid);

	/**
	 * Authorizations are not batched and not disabled
	 * 
	 * @param businessUnitUuid
	 * @param creditCardUuid
	 */
	public List<T> findAuthorizationsActive(String businessUnitUuid, String creditCardUuid);

	/**
	 * Check if exist some authorization without take into account if it is disabled or batched.
	 * 
	 * @param businessUnitUuid
	 * @param creditCardUuid
	 */
	public boolean isAuthorizationExist(String businessUnitUuid, String creditCardUuid);

	/**
	 * Set the authorization as no batched. It indicates that the authorization was not captured, settled
	 * 
	 * @param authorization
	 */
	public void unBatched(T authorization);
}
