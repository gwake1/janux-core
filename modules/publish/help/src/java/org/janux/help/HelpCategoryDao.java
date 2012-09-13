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

import org.janux.bus.persistence.DataAccessObject;
import org.janux.bus.persistence.EntityNotFoundException;


public interface HelpCategoryDao extends DataAccessObject
{
	/** 
	 * loads a HelpCategory object from persistence using its id
	 *
	 * @param id the internal identifier of the HelpCategory
	 * @throws EntityNotFoundException if a HelpEntry object with that id is not found
	 */
	public HelpCategory load(Integer id) throws EntityNotFoundException;

	public HelpCategory newHelpCategory(String title);
	
	public List<HelpCategory> loadAll();
	
	public HelpCategory findByTitle(String title);

	public void delete(HelpCategory entry);

}
