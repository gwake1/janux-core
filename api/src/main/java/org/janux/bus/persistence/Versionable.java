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

package org.janux.bus.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 ***************************************************************************************************
 * Interface for implementing objects that expose the creation and last modification date of an
 * entity.  This information is useful for simple audits of the data, and the modification timestamp
 * can be used for managing long transactions, for example by using the &lt;version&gt; optional
 * hibernate attribute. 
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4
 ***************************************************************************************************
 */
public interface Versionable
{
	/** 
	 * Date on which this entity was last modified which can be used for a quick audit of the data on
	 * this table; this field can be used by the optional hibernate &lt;version&gt; element, for
	 * example, to indicate that the table contains versioned data, and is useful when using long
	 * transactions.
	 */
	Date getDateUpdated();
	void setDateUpdated(Date d);

	/** 
	 * The timestamp at which this entity was first saved/persisted.
	 */
	Date getDateCreated();
	void setDateCreated(Date d);
}
