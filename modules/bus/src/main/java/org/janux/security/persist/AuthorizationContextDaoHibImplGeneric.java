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

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.janux.security.*;
import org.janux.security.persist.*;
import org.janux.security.metadata.*;

import org.janux.bus.persistence.EntityNotFoundException;
import org.janux.bus.persistence.GenericDaoHibImpl;
import org.janux.bus.search.SearchCriteria;
import org.janux.util.Chronometer;
import org.janux.util.SortOrderComparator;
import org.springframework.dao.DataAccessException;


/**
 * @author  <a href="mailto:philippe.paravicini@janux.biz">Philippe Paravicini</a>
 * @see AuthorizationContextDaoGeneric
 * @since 0.4
 */
public class AuthorizationContextDaoHibImplGeneric 
	extends GenericDaoHibImpl<AuthorizationContextImpl, Integer, SearchCriteria>
	implements AuthorizationContextDaoGeneric<AuthorizationContextImpl>
{
	public SortedSet<AuthorizationContext> loadAll()
	{
		Chronometer timer = new Chronometer(true);
		if (log.isDebugEnabled()) log.debug("attempting to load all permission contexts...");

		@SuppressWarnings("unchecked")
		List<AuthorizationContextImpl> list = getHibernateTemplate().loadAll(this.getPersistentClass());

		SortedSet<AuthorizationContext> set = new TreeSet<AuthorizationContext>(new SortOrderComparator());

		for (AuthorizationContext context : list) {
			if (log.isDebugEnabled()) log.debug(context.getName() + ": " + context);
			set.add(context);
		}

		if (log.isInfoEnabled()) log.info("successfully retrieved all " + set.size() + " permission contexts in " + timer.printElapsedTime());

		return set;
	}


	public AuthorizationContext findByName(String name)
	{
		Chronometer timer = new Chronometer(true);

		if (log.isDebugEnabled()) log.debug("attempting to find AuthorizationContext with name '" + name + "'");

		List list = getHibernateTemplate().find("from AuthorizationContextImpl where name=?", name);

		AuthorizationContext context = (list.size() > 0) ? (AuthorizationContext)list.get(0) : null;

		if (context == null) {
			log.warn("Unable to find AuthorizationContext with name: '" + name + "'");
			return null;
		}

		if (log.isInfoEnabled()) log.info("successfully retrieved context with name: '" + name + "' in " + timer.printElapsedTime());
		if (log.isDebugEnabled()) log.debug(context.toString());

		return context;
	}


	public AuthorizationContext loadByName(String name) throws EntityNotFoundException
	{
		AuthorizationContext context = this.findByName(name);

		if (context == null) 
			throw new EntityNotFoundException("Unable to retrieve context with name: '" + name + "'");

		return context;
	}


	public AuthorizationContext newAuthorizationContext()
	{
		return new AuthorizationContextImpl();
	}
}
