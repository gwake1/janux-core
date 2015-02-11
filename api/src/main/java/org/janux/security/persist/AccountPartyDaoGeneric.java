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

package org.janux.security.persist;

import java.util.SortedSet;

import org.janux.security.Account;
import org.janux.security.AccountParty;
import org.janux.security.AccountPartyFacet;
import org.janux.bus.persistence.GenericDaoReadOnlyWithFacets;
import org.janux.bus.persistence.GenericDaoWrite;
import org.janux.bus.search.SearchCriteria;

import biz.janux.people.Party;

public interface AccountPartyDaoGeneric<T extends AccountParty> 
	extends GenericDaoWrite<T, Integer>,
	GenericDaoReadOnlyWithFacets<T, Integer, SearchCriteria, AccountPartyFacet>
{
	
	/** Loads all AccountParties defined in the system */
	public SortedSet<AccountParty> loadAll();
	
	public Account getAccount(Integer partyId);
	
	public Party getParty(Integer accountId);

}
