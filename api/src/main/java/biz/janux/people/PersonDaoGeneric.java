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

import org.janux.bus.persistence.GenericDaoReadOnlyWithFacets;
import org.janux.bus.persistence.GenericDaoWrite;
import org.janux.bus.search.SearchCriteria;

/**
 * 
 * Used to create, save, retrieve, update and delete Person objects from
 * persistent storage
 *
 * @author  <a href="mailto:philippe.paravicini@eCommerceStudio.com">Philippe Paravicini</a>
 * @version $Id: PersonDao.java,v 1.4 2006-03-21 23:44:00 dfairchild Exp $
 *
 */

public interface PersonDaoGeneric<T extends Person> 
	extends GenericDaoWrite<T, Integer>,
	GenericDaoReadOnlyWithFacets<T, Integer, SearchCriteria, PersonFacet>
{
						
	
}
