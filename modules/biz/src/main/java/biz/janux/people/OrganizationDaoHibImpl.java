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

import org.janux.bus.persistence.DataAccessHibImplAbstract;
import org.janux.bus.persistence.GenericDaoHibImpl;

import org.hibernate.Hibernate;

import org.janux.bus.persistence.EntityNotFoundException;

/**
 * Used to create, save, retrieve, update and delete Organization objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@janux.biz">Philippe Paravicini</a>
 * @version $Id: OrganizationDaoHibImpl.java,v 1.7 2005-12-21 02:12:30 philippe Exp $
 * 
 * @deprecated use {@link OrganizationDaoHibImplGeneric} 
 */
public class OrganizationDaoHibImpl extends DataAccessHibImplAbstract implements OrganizationDao 
{
	/* 
	 * deep loads a Organization object from persistence using its id, this includes
	 * all the ContactMethods
	 */
	public Organization load(Integer id) throws EntityNotFoundException
	{
		if (log.isDebugEnabled()) log.debug("attempting to retrieve Organization with id '" + id + "'");

		Organization org = (Organization)getHibernateTemplate().load(OrganizationImpl.class, id);

		initialize(org);

		/*
		Hibernate.initialize(org.getEmailAddresses());
		Hibernate.initialize(org.getUrls());
		Hibernate.initialize(org.getPhoneNumbers());
		Hibernate.initialize(org.getPostalAddresses());
		*/

		if (org != null)
			if (log.isInfoEnabled()) log.info("successfully retrieved: " + org); 
		else
			log.warn("unable to find Organization with id: '" + id + "'");

		return org;
	}

	
	public Organization findByCode(String code)
	{
		long begin = System.currentTimeMillis();

		if (log.isDebugEnabled()) log.debug("attempting to find Organization with code '" + code + "'");

		List list = getHibernateTemplate().find("from OrganizationImpl where code=?", code);

		Organization org = (list.size() > 0) ? (Organization)list.get(0) : null;

		if (org == null) {
			log.warn("Unable to find Organization with code: '" + code + "'");
			return null;
		}

		initialize(org);

		/*
		Hibernate.initialize(org.getEmailAddresses());
		Hibernate.initialize(org.getUrls());
		Hibernate.initialize(org.getPhoneNumbers());
		Hibernate.initialize(org.getPostalAddresses());
		*/

		if (log.isInfoEnabled()) log.info("successfully retrieved organization with code: '" + code + "' in " + (System.currentTimeMillis() - begin) + " ms" ); 

		return org;
	}


	public Organization loadByCode(String code) throws EntityNotFoundException
	{
		Organization org = this.findByCode(code);

		if (org == null) 
			throw new EntityNotFoundException("Unable to retrieve organization with code: '" + code + "'");

		return org;
	}


	public List loadAll()
	{
		long begin = System.currentTimeMillis();
		if (log.isDebugEnabled()) log.debug("attempting to load all organizations...");

		List list = getHibernateTemplate().loadAll(OrganizationImpl.class);

		if (log.isInfoEnabled()) recordTime("successfully retrieved all " + list.size() + " organizations", begin);

		return list;
	}


	/** actively loads (initializes) ContactMethod fields on a newly-retrieved Hotel object */
	private void initialize(Organization org) 
	{
		Hibernate.initialize(org.getContactMethods());
		/*
		Hibernate.initialize(org.getEmailAddresses());
		Hibernate.initialize(org.getUrls());
		Hibernate.initialize(org.getPhoneNumbers());
		Hibernate.initialize(org.getPostalAddresses());
		*/
	}

}
