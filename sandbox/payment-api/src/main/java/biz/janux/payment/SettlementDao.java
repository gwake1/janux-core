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

package biz.janux.payment;

import java.io.Serializable;

import org.janux.bus.persistence.GenericDaoReadOnlyWithFacets;
import org.janux.bus.persistence.GenericDaoWrite;
import org.janux.bus.search.SearchCriteria;

public interface SettlementDao<T extends Settlement,ID extends Serializable,S extends SearchCriteria,F extends Enum<F>> 
extends GenericDaoWrite<T, ID>, 
GenericDaoReadOnlyWithFacets<T, ID, S, F>
{
	/**
	 * Return the last batch number used checking the approved settlement responses
	 */
	public int getLastBatchNumber();
}
