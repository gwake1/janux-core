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

import org.janux.bus.persistence.GenericDaoWithFacetsHibImpl;
import org.janux.bus.search.SearchCriteria;
import org.springframework.dao.DataAccessException;

/**
 * Used to create, save, retrieve, update and delete Person objects from persistent storage.
 * TODO - pp-20101103: need to properly define the 'initialize' method
 *
 * @author  <a href="mailto:philippe.paravicini@janux.biz">Philippe Paravicini</a>
 * @version $Id: PersonDaoHibImpl.java,v 1.6 2005-12-21 02:12:30 philippe Exp $
 *
 */
public class PersonDaoHibImplGeneric 
	extends GenericDaoWithFacetsHibImpl<PersonImpl, Integer, SearchCriteria, PersonFacet>
	implements PersonDaoGeneric<PersonImpl>
{
	public PersonImpl load(PersonImpl person, List<PersonFacet> facetSet) throws DataAccessException{
		/*if(facetSet.contains(PersonFacet.EMAIL_ADDRESSES)){
			Hibernate.initialize(person.getEmailAddresses());
		}
		if(facetSet.contains(PersonFacet.URLS)){
			Hibernate.initialize(person.getUrls());
		}
		if(facetSet.contains(PersonFacet.PHONE_NUMBERS)){
			Hibernate.initialize(person.getPhoneNumbers());
		}
		if(facetSet.contains(PersonFacet.POSTAL_ADDRESSES)){
			Hibernate.initialize(person.getPostalAddresses());
		}*/
		getHibernateTemplate().initialize(person.getContactMethods());
		return person;
	}


	public void initialize(PersonImpl person, PersonFacet facet) {
		// does nothing for now
	}


	/*private void initialize(Person person) 
	{
		Hibernate.initialize(person.getContactMethods());
	}*/
	
}
