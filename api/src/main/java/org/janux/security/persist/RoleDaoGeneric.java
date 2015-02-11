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

package org.janux.security.persist;

import java.util.SortedSet;

import org.janux.security.Role;
import org.janux.bus.persistence.EntityNotFoundException;
import org.janux.bus.persistence.GenericDaoReadOnly;
import org.janux.bus.persistence.GenericDaoWrite;
import org.janux.bus.search.SearchCriteria;

/**
 * Used to create, save, retrieve, update and delete Role objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4
 */
public interface RoleDaoGeneric<T extends Role> 
	extends GenericDaoWrite<T, Integer>,
	GenericDaoReadOnly<T, Integer, SearchCriteria>

{
	/** 
	 * Loads all Roles defined in the system, along with their Permissions and SubRoles;
	 * a SubRole may appear individually, as well as part of the children of the 
	 * Super Roles that may contain it
	 */
	SortedSet<Role> loadAll();


	/**
	 * Returns an Role by names, or <code>null</code> if the Role is not found.
	 *
	 * @param name the Role name
	 */
	Role findByName(String name);


	/**
	 * loads an Role object, or throws exception if Role with that name is not found
	 *
	 * @param name a name that uniquely identifies this role
	 *
	 * @throws EntityNotFoundException if a Role object with that name is not found
	 */
	Role loadByName(String name) throws EntityNotFoundException;


	/** returns a new Role instance */
	Role newRole();
}
