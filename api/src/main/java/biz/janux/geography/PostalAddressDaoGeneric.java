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

import java.util.List;

import org.janux.bus.persistence.GenericDaoReadOnly;
import org.janux.bus.persistence.GenericDaoWrite;
import org.janux.bus.search.SearchCriteria;

/**
 ***************************************************************************************************
 * Data Access Class used to access and manage PostalAddress entities
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 2008-03-27
 ***************************************************************************************************
 */
public interface PostalAddressDaoGeneric<T extends PostalAddress> 
	extends GenericDaoWrite<T, Integer>,
	GenericDaoReadOnly<T, Integer, SearchCriteria>
{

	/** 
	 * Counts PostalAddress entities where the address.getCountryAsString is not null, that is, where
	 * the Country is not stored as a reference; this is mostly meant as a utility method used to
	 * clean up or convert data
	 *
	 * @see PostalAddress#getCountryAsString
	 * @see GeographyService#setCityStateCountry
	 */
	public int countByCountryAsString();

	/** 
	 * Retrieves PostalAddress entities where address.getCountryAsString is not null; this is
	 * mostly meant as a utility method that can be used to clean up or convert data
	 *
	 * @see PostalAddress#getCountryAsString
	 * @see GeographyService#setCityStateCountry
	 */
	public List<PostalAddress> findByCountryAsString();

	/** 
	 * Retrieves PostalAddress entities where address.getCountryAsString is not null;
	 * this is mostly meant as a utility method that can be used to clean up or convert data
	 *
	 * @see PostalAddress#getCountryAsString
	 * @see GeographyService#setCityStateCountry
	 *
	 * @param numRecords the maximum number of PostalAddresses to return (equivalent to LIMIT modifier in SQL Selects)
	 * @param offset when limiting the number of records returned, the 
	 */
	public List<PostalAddress> findByCountryAsString(Integer numRecords, Integer offset);

	/** 
	 * Count PostalAddress entities where the address.getCountryAsString field matches the country
	 * string passed; this is mostly meant as a utility method that can be used to clean up or convert data
	 *
	 * @see PostalAddress#getCountryAsString
	 * @see GeographyService#setCityStateCountry
	 *
	 * @param country a string used to match against the PostalAddress.getCountryAsString
	 */
	public int countByCountryAsString(String country);

	/** 
	 * Retrieves PostalAddress entities where the address.getCountryAsString field matches the country
	 * string passed; this is mostly meant as a utility method that can be used to clean up or convert data
	 *
	 * @see PostalAddress#getCountryAsString
	 * @see GeographyService#setCityStateCountry
	 *
	 * @param country a string used to match against the PostalAddress.getCountryAsString
	 */
	public List<PostalAddress> findByCountryAsString(String country);

	/** 
	 * Retrieves PostalAddress entities where the address.getCountryAsString field matches the country
	 * string passed; this is mostly meant as a utility method that can be used to clean up or convert data
	 *
	 * @see PostalAddress#getCountryAsString
	 * @see GeographyService#setCityStateCountry
	 *
	 * @param country a string used to match against the PostalAddress.getCountryAsString
	 * @param numRecords the maximum number of PostalAddresses to return (equivalent to LIMIT modifier
	 * in SQL Selects)
	 * @param offset when limiting the number of records returned, the 
	 */
	public List<PostalAddress> findByCountryAsString(String country, Integer numRecords, Integer offset);


}
