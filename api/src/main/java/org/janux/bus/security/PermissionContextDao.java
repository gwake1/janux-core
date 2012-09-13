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

package org.janux.bus.security;

import java.util.SortedSet;

import org.janux.bus.persistence.DataAccessObject;
import org.janux.bus.persistence.EntityNotFoundException;

/**
 * Used to create, save, retrieve, update and delete PermissionContext objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.1
 * @deprecated use {@link PermissionContextDaoGeneric}
 */
public interface PermissionContextDao extends DataAccessObject
{
	/**  Loads all PermissionContexts defined in the system, sorted by sortOrder */
	public SortedSet<PermissionContext> loadAll();


	/**
	 * Returns a PermissionContext by names, or <code>null</code> if the PermissionContext is not found.
	 *
	 * @param name the PermissionContext name
	 */
	public PermissionContext findByName(String name);


	/**
	 * loads an PermissionContext object, or throws exception if PermissionContext with that name is not found
	 *
	 * @param name a name that uniquely identifies this PermissionContext
	 *
	 * @throws EntityNotFoundException if a PermissionContext object with that name is not found
	 */
	public PermissionContext loadByName(String name) throws EntityNotFoundException;


	/** returns a new PermissionContext instance */
	public PermissionContext newPermissionContext();
}
