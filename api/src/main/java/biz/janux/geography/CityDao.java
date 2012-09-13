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

package biz.janux.geography;

import org.janux.bus.persistence.EntityNotFoundException;

import org.janux.bus.persistence.DataAccessObject;


/**
 ***************************************************************************************************
 * Data Access Class used to access and manage City entities
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 2006-02-17
 * 
 * @deprecated use {@link biz.janux.geography.CityDaoGeneric}
 ***************************************************************************************************
 */
public interface CityDao extends DataAccessObject
{
	/** 
	 * loads a City object from persistence using its id
	 *
	 * @param id the internal identifier of the City
	 * @throws EntityNotFoundException if a City object with that id is not found
	 */
	public City load(Integer id) throws EntityNotFoundException;

	/** 
	 * retrieves a City within a country and state by performing a
	 * cap-insensitive search by name, or returns <code>null</code> if a City
	 * with that name is not found 
	 *
	 * @param state a StateProvince that has been persisted in the system
	 * @param cityName the name of a city in the default system language
	 */
	public City findByName(StateProvince state, String cityName);

	/** 
	 * retrieves a City within a country and state by performing a
	 * cap-insensitive search by name, and using the ISO country code and a state
	 * code, or returns <code>null</code> if a City with that name is not found 
	 *
	 * @param countryCode a two-letter ISO country code
	 * @param stateCode a country-specific state abbreviation code
	 * @param cityName the name of a city in the default system language
	 */
	public City findByName(String countryCode, String stateCode, String cityName);

	/** 
	 * instantiates a new City within the specified StateProvince, 
	 * with the name provided
	 *
	 * @param state a name that has been persisted in the system
	 * @param cityName the name for the new system, in the default system
	 * language
	 */
	public City newCity(StateProvince state, String cityName);

}
