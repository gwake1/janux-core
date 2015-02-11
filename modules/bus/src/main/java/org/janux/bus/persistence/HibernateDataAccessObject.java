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

import org.springframework.dao.DataAccessException;

import org.springframework.orm.hibernate3.HibernateTemplate;

import org.hibernate.Session;

/**
 ***************************************************************************************************
 * This interface contains convenience methods that are Hibernate specific; they save the trouble of
 * calling getHibernateTemplate(), and it provides a non-protected accessor for the current Hibernate
 * Session, which can be useful when troubleshooting Hibernate issues
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.2.05
 ***************************************************************************************************
 * @deprecated use {@link HibernateDataAccessObjectGeneric}
 */
public interface HibernateDataAccessObject extends DataAccessObject
{
	void evict(final Object persistentObject) throws DataAccessException;

	void flush();

	void clear();

	HibernateTemplate getHibernateTemplate();

	Session getHibernateSession();

	/**
	 * Reattach an object with the current session. 
	 * This will not execute a select to the database so the detached instance has to be unmodified.
	 * 
	 * @param persistentObject
	 * @throws DataAccessException
	 */
	public void attachClean(final Object persistentObject) throws DataAccessException;
}

