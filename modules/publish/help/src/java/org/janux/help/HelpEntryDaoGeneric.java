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

import org.janux.bus.persistence.EntityNotFoundException;
import org.janux.bus.persistence.GenericDaoReadOnlyWithFacets;
import org.janux.bus.persistence.GenericDaoWrite;
import org.janux.bus.search.SearchCriteria;

public interface HelpEntryDaoGeneric<T extends HelpEntry> extends GenericDaoWrite<T, Integer>,GenericDaoReadOnlyWithFacets<T, Integer, SearchCriteria, HelpEntryFacet>
{
	public HelpEntry loadByCode(String code) throws EntityNotFoundException;

	public HelpEntry findByCode(String code);
	public HelpEntry findByLabel(String label);

	public HelpEntry newHelpEntry(String code,String label,HelpCategory category);
	
	public List<HelpEntry> loadAll();

	public void delete(HelpEntry entry);

}
