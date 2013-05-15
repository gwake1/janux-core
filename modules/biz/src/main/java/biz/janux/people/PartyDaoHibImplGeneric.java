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
import java.util.List;

import org.hibernate.Hibernate;
import org.janux.bus.persistence.GenericDaoWithFacetsHibImpl;
import org.janux.bus.search.SearchCriteria;
import org.springframework.dao.DataAccessException;

/**
 * Used to create, save, retrieve, update and delete Party objects from persistent storage.
 * TODO - pp-20101103: need to properly define the 'initialize' method
 *
 *
 * @author  <a href="mailto:philippe.paravicini@janux.biz">Philippe Paravicini</a>
 * @since 0.4
 */

public class PartyDaoHibImplGeneric 
	extends GenericDaoWithFacetsHibImpl<Party, Integer, SearchCriteria, PartyFacet> 
	implements PartyDaoGeneric<Party>
{
	public Party load(Party party, List<PartyFacet> facetSet) throws DataAccessException{
		if(facetSet!=null && facetSet.contains(PartyFacet.CONTACT_METHODS)){
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
			}
		}
		return party;
	}
	
	public void initialize(Party party, PartyFacet facet) {
		throw new UnsupportedOperationException();
	}


/*	private void initialize(Party party) 
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
		}
		
		
	}*/
}
