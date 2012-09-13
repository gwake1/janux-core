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

import java.util.Date;
import java.sql.Timestamp;

/**
 ***************************************************************************************************
 * Utility class that provides an Integer identity field, and date created/updated fields
 * 	
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4
 ***************************************************************************************************
 */
public abstract class PersistentVersionableAbstract extends PersistentAbstract 
	implements Versionable
{
	private Date dateCreated;
	private Date dateUpdated;

  public Date getDateCreated() 
	{
		if (this.dateCreated == null) {this.dateCreated = new Date();}
    return this.dateCreated;
  }

  public void setDateCreated(Date datetime) {
    // prevents rounding issues in unit tests when using hibernate
    if (datetime instanceof Timestamp) {
      ((Timestamp)datetime).setNanos(0);
    }
    this.dateCreated = datetime;
  }


  public Date getDateUpdated() {
    return this.dateUpdated;
  }

  public void setDateUpdated(Date datetime) {
    // prevents rounding issues in unit tests
    if (datetime instanceof Timestamp) {
      ((Timestamp)datetime).setNanos(0);
    }
    this.dateUpdated = datetime;
  }
	
} // end class PersistentVersionableAbstract
