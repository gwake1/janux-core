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


public class HelpEntryImpl extends HelpNodeImpl implements HelpEntry, java.io.Serializable
{
	private static final long serialVersionUID = 5110867069644860623L;
	private String code;
	private String label;
	private String text;
	private HelpCategory category;

	public HelpEntryImpl() {}
	
	public HelpEntryImpl(String code,String label,HelpCategory category)
	{
		super();
		
		this.code = code;
		this.label = label;
		this.category = category;
	}
	
	public String getLabel()
	{
		return label;
	}

	public void setLabel(String name)
	{
		this.label = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HelpCategory getCategory()
	{
		return category;
	}

	public void setCategory(HelpCategory category)
	{
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
