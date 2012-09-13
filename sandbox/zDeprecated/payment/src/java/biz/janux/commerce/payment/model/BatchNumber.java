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

package biz.janux.commerce.payment.model;

/**
 * 
 * @author Nilesh
 * 
 * This Model is mapped to batch_number table
 * It keeps the record of generated batchNumber
 *
 */
public class BatchNumber {

	int hotelId;
	int batchNumberID;
	int number;
	/**
	 * 
	 * @return int
	 */
	public int getBatchNumberID() {
		return batchNumberID;
	}
	/**
	 * 
	 * @param batchNumberID
	 */
	public void setBatchNumberID(int batchNumberID) {
		this.batchNumberID = batchNumberID;
	}
	/**
	 * 
	 * @return int
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * 
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * 
	 * @return int
	 */
	public int getHotelId() {
		return hotelId;
	}
	/**
	 * 
	 * @param hotelId
	 */
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
}
