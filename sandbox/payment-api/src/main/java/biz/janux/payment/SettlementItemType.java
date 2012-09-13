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

/**
 * CREDIT
 *  
 * When money needs to be returned to a customer, a credit transaction is used. 
 * This is sometimes confused with a void, but the two are very different. 
 * A credit is used to return all or part of the funds of a previously captured transaction back 
 * to the customer.
 *  
 * A void is simply when a previously authorized transaction does not get captured, 
 * so funds are never captured and no charge appears to the customer.
 * 
 * This can be done at any time or along with the rest of the transactions during the normal settlement 
 * process.
 * 
 * The credit is not tied to a particular previous transaction. 
 *
 * 
 * PURCHASE
 * 
 * Indicates that a authorization has to be captured
 * 
 * Capture is the act of actually transferring the previously authorized funds 
 * from the card holder bank account to the merchant bank account. 
 * 
 * @author albertobuffagni@gmail.com
 *
 */
public enum SettlementItemType {
	PURCHASE,CREDIT;
}
