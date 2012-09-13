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
 * 
 * Represents a Address Verification Transaction
 * 
 * AVS (Address Verification Service) is where the address information of the customer 
 * is checked for accuracy during the authorization request to help prevent fraud. 
 * This information consists of a street address and zip code.
 * After the authorization is sent, the ResponseAVS property will contain the results 
 * of the address verification which can be used by the merchant to decide 
 * whether or not to honor the transaction and capture the funds or let it void.
 * 
 * @author Nilesh
 * @author albertobuffagni@gmail.com
 */
public interface AddressVerification extends Transaction<AddressVerificationResponse> {
}
