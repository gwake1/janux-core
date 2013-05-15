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

import java.util.List;

import org.janux.bus.search.SearchCriteria;


public interface SettlementService <T extends Settlement>{

	public List<T> findAll(SearchCriteria searchCriteria);
	
	public T find(SearchCriteria searchCriteria);
	
	public T load(T settlement);

	public T create(T settlement);

	/**
	 * Return the last batch number used checking the approved settlement responses
	 */
	public int findLastBatchNumber(MerchantAccount merchantAccount);
	
	public void saveOrUpdateBatchNumber(MerchantAccount merchantAccount,int number);
	
	/**
	 * Return the last Transaction Sequence Number
	 * 
	 * This 4-character numeric field contains a terminal-generated transaction
	 * sequence number to be submitted in all authorization request messages.
	 * This number is echoed back to the terminal for purposes of assisting in
	 * the matching of authorization request and response messages. This value
	 * must be in the range of 0001 - 9999 and is incremented on each
	 * authorization request message. This number is automatically incremented
	 * from 9999 to 0001.
	 * 
	 * @param businessUnit
	 */
	public int getLastTransactionSequenceNumber(BusinessUnit businessUnit);
	
}
