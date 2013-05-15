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

import java.util.HashSet;
import java.util.Set;


public class HelpCategoryImpl extends HelpNodeImpl implements HelpCategory, java.io.Serializable
{
	private static final long serialVersionUID = -5693578080066840951L;
	private String title;
	private String description;
	private Set<HelpEntry> helpEntries = new HashSet<HelpEntry>();
	
	public HelpCategoryImpl() {}
	
	public HelpCategoryImpl(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Set<HelpEntry> getHelpEntries()
	{
		return helpEntries;
	}

	public void setHelpEntries(Set<HelpEntry> entries)
	{
		this.helpEntries = entries;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
