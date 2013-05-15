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

package biz.janux.people;

import java.util.List;

import org.janux.bus.persistence.EntityNotFoundException;
import org.janux.bus.persistence.GenericDaoReadOnlyWithFacets;
import org.janux.bus.persistence.GenericDaoWrite;
import org.janux.bus.search.SearchCriteria;

/**
 * 
 * Used to create, save, retrieve, update and delete Organization objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@eCommerceStudio.com">Philippe Paravicini</a>
 * @version $Id: OrganizationDao.java,v 1.7 2006-05-06 02:00:21 dfairchild Exp $
 *
 */

public interface OrganizationDaoGeneric<T extends Organization> 
	extends GenericDaoWrite<T, Integer>,
	GenericDaoReadOnlyWithFacets<T, Integer, SearchCriteria, OrganizationFacet>
{
	/**
	 * Returns a list of all Organizations in the system; the organization objects returned should
	 * have at least their id, code and name fields initialized (retrieved from persistence) but need
	 * not be fully initialized; that is, the full object graph that includes the main organization
	 * object and its associated associations (Contact Methods, associations, etc....) may not have
	 * been fully retrieved.
	 */
	public List loadAll();

	/**
	 * Deep loads an Organization object, or throws exception if Organization with
	 * that code is not found
	 *
	 * @param code a business code that uniquely identifies this Organization
	 * @return an object graph representing this Organization and including associated objects
	 *
	 * @throws EntityNotFoundException if a Organization object with that id is not found
	 */
	public Organization loadByCode(String code) throws EntityNotFoundException;


	/**
	 * Returns a possibly lightweight representation of the corresponding Organization, which may not
	 * contain all associated objects, or <code>null</code> if the Organization is not found.
	 *
	 * @param code a business code that uniquely identifies this Organization
	 */
	public Organization findByCode(String code);


}
