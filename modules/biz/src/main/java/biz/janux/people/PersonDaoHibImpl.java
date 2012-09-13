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

import org.hibernate.Hibernate;
import org.janux.bus.persistence.DataAccessHibImplAbstract;
import org.springframework.dao.DataAccessException;

/**
 * 
 * Used to create, save, retrieve, update and delete Person objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@janux.biz">Philippe Paravicini</a>
 * @version $Id: PersonDaoHibImpl.java,v 1.6 2005-12-21 02:12:30 philippe Exp $
 *
 * @deprecated use {@link PersonDaoHibImplGeneric}
 */
public class PersonDaoHibImpl extends DataAccessHibImplAbstract implements PersonDao 
{
	/* 
	 * deep loads a Person object from persistence using its id, this includes
	 * all the ContactMethods
	 */
	public Person load(Integer id) throws DataAccessException
	{
		if (log.isDebugEnabled()) log.debug("attempting to retrieve Person with id '" + id + "'");

		Person person = (Person)getHibernateTemplate().load(PersonImpl.class, id);

		initialize(person);
		/*
		Hibernate.initialize(person.getEmailAddresses());
		Hibernate.initialize(person.getUrls());
		Hibernate.initialize(person.getPhoneNumbers());
		Hibernate.initialize(person.getPostalAddresses());
		*/

		if (person != null)
			if (log.isInfoEnabled()) log.info("successfully retrieved: " + person); 
		else
			log.warn("unable to find Person with id: '" + id + "'");

		return person;
	}

	private void initialize(Person person) 
	{
		Hibernate.initialize(person.getContactMethods());
	}
}
