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

package org.janux.bus.persistence;

import java.io.Serializable;
import java.util.List;

import org.janux.bus.search.SearchCriteria;

/**
 ***************************************************************************************************
 * Interface for a Data Access Object that can be used for a single specified
 * type domain object for read operations. 
 * A single instance implementing this interface can be used
 * only for the type of domain object specified in the type parameters.
 * 
 * 
 * @param <T>
 *            The type of the domain object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the domain object for which this instance is
 *            to be used.
 * @param <S>
 *            The type of criteria strategy used for searching objects.
 *
 * @author  <a href="mailto:moradaniel@janux.org">Daniel Mora</a>
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 ***************************************************************************************************
 */

public interface GenericDaoReadOnly<T, ID extends Serializable, S extends SearchCriteria>
{
	/** 
	 * TODO
	 * loads an object from persistence using its id
	 *
	 * @throws DataAccessException if the object with that id is not found
	 */
	public T load(ID id) throws DataAccessException;
	
	/**
	 * TODO
	 * Search for objects considering the given search criteria
	 * @param searchCriteria
	 * @return PageList<T>
	 */
	public List<T> findByCriteria(S searchCriteria);
	
	/**
	 * TODO
	 * @param searchCriteria
	 */
	public int count(S searchCriteria);
}
