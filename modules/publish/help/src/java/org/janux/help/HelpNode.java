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

import java.util.Date;

public interface HelpNode
{
	public Date getCreated();

	public void setCreated(Date creationDate);

	public Date getModified();

	public void setModified(Date modificationDate);

	public int getSortOrder();

	public void setSortOrder(int sortOrder);

}