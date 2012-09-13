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

import java.util.Iterator;

import org.springframework.dao.DataAccessException;
import org.janux.bus.persistence.DataAccessHibImplAbstract;
import org.janux.bus.persistence.GenericDaoHibImpl;

import org.hibernate.Hibernate;

/**
 * 
 * Used to create, save, retrieve, update and delete Party objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@janux.biz">Philippe Paravicini</a>
 * @version $Id: PartyDaoHibImpl.java,v 1.6 2006-04-17 21:49:14 dfairchild Exp $
 *
 * @deprecated use {@link GenericDaoHibImpl}
 */
public class PartyDaoHibImpl extends DataAccessHibImplAbstract implements PartyDao 
{
	/* 
	 * deep loads a Party object from persistence using its id, this includes
	 * all the ContactMethods
	 */
	public Party load(Integer id) throws DataAccessException
	{
		if (log.isDebugEnabled()) log.debug("attempting to retrieve Party with id '" + id + "'");

		Party party = (Party)getHibernateTemplate().load(Party.class, id);

		initialize(party);
		/*
		Hibernate.initialize(party.getEmailAddresses());
		Hibernate.initialize(party.getUrls());
		Hibernate.initialize(party.getPhoneNumbers());
		Hibernate.initialize(party.getPostalAddresses());
		*/

		if (party != null)
			if (log.isInfoEnabled()) log.info("successfully retrieved: " + party); 
		else
			log.warn("unable to find Party with id: '" + id + "'");

		return party;
	}

	private void initialize(Party party) 
	{
		Hibernate.initialize(party.getContactMethods());
		
		final Iterator it = party.getContactMethods().values().iterator();
		while (it.hasNext())
		{
			Object obj = it.next();
			if (obj instanceof ContactMethod) 
			{
				final ContactMethod cm = (ContactMethod ) obj;
				Hibernate.initialize(cm);
			}
		} // end for
		
		
	}
}
