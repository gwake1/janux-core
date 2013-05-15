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

package org.janux.help;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.janux.bus.persistence.GenericDaoWithFacetsHibImpl;
import org.janux.bus.search.SearchCriteria;
import org.janux.util.Chronometer;
import org.springframework.dao.DataAccessException;


public class HelpCategoryDaoHibImplGeneric extends GenericDaoWithFacetsHibImpl<HelpCategoryImpl, Integer, SearchCriteria, HelpCategoryFacet> implements HelpCategoryDaoGeneric<HelpCategoryImpl>
{
	public HelpCategoryImpl load(HelpCategoryImpl helpCategory, List<HelpCategoryFacet> facetSet) throws DataAccessException{
		return null;
	}
	
	public void initialize(HelpCategoryImpl helpCategory, HelpCategoryFacet facet) {
		throw new UnsupportedOperationException();
	}


	public HelpCategory newHelpCategory(String title)
	{
		return new HelpCategoryImpl(title);
	}
	
	public HelpCategory findByTitle(String title)
	{
		Chronometer timer = new Chronometer(true);
		final Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
		criteria.add( Restrictions.eq("title", title).ignoreCase() );
		List matchList = criteria.list();
		HelpCategory entry = (matchList.size() > 0) ? (HelpCategory) matchList.get(0) : null;

		if (entry != null) {
			log.debug("Help Category: " + entry.getTitle());
			if (log.isInfoEnabled()) log.info("successfully retrieved HelpCategory '" + title + "' in " + timer.printElapsedTime());
		}
		else
			log.warn("unable to find Help Category with title: '" + title + "'");

		return entry;
	}

	public void delete(HelpCategory entry)
	{
		getHibernateTemplate().delete(entry);
	}


	@SuppressWarnings("unchecked")
	public List<HelpCategory> loadAll()
	{
		Chronometer timer = new Chronometer(true);
		if (log.isDebugEnabled()) log.debug("attempting to load all help categories...");

		List list = getHibernateTemplate().loadAll(this.getPersistentClass());

		if (log.isInfoEnabled()) log.info("successfully retrieved all '" + list.size() + "' HelpCategories in " + timer.printElapsedTime());

		return list;
	}

}
