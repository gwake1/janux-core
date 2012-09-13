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

package biz.janux.commerce.payment.implementation.common;

import java.util.ArrayList;

import biz.janux.commerce.payment.interfaces.service.TransactiontHistoryService;
import biz.janux.commerce.payment.interfaces.transaction.Settlement;
import biz.janux.commerce.payment.interfaces.transaction.Transaction;
/**
 * @author Nilesh
 * TODO need to implements methods in future 
 * for getting records of  Settlements and Transactions
 * 
 **/
public class HistoryServiceImpl implements TransactiontHistoryService {

	public ArrayList<Settlement> getSettlements(long merchantId,
			long instrumentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Transaction> getTransactions(long merchantId,
			long instrumentId) {
		// TODO Auto-generated method stub
		return null;
	}
}
