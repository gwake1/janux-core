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

import org.janux.bus.persistence.DataAccessException;



/**
 * 
 * Used to create, save, retrieve, update and delete Party objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Id: PartyDao.java,v 1.3 2006-02-18 03:05:46 philippe Exp $
 *
 * @deprecated use {@link PartyDaoGeneric}
 */
public interface PartyDao
{
	/** 
	 * loads a Party object from persistence using its id
	 *
	 * @throws DataAccessException if a Party object with that id is not found
	 */
	public Party load(Integer id) throws DataAccessException;

}
