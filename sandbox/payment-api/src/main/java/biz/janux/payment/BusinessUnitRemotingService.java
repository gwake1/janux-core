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
 * This service is a facade of the {@link BusinessUnitService}. 
 *    
 * @author albertobuffagni@gmail.com
 *
 */
public interface BusinessUnitRemotingService extends RemotingService{

	/**
	 * save or update a {@link BusinessUnit}
	 * If the uuid is null the entity is new
	 * @param businessUnit
	 * @param uuid
	 */
	public BusinessUnit save(BusinessUnit businessUnit,String uuid);
	
	/**
	 * If the business unit has transactions associated , it is disabled. 
	 * Otherwise the merchant account is removed. 
	 * @param uuid
	 */
	public void delete(String uuid);

}
