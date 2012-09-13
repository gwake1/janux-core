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
 * </br>
 * This Model is mapped to country table
 */
public class Country {
	
	private long countryId;
	
	private String title;
	
	private String code;
	
	private String iso;
	
	private String iso3letter;
	
	private String iso3numeric;
	/**
	 * 
	 * @return long
	 */
	public long getCountryId() {
		return countryId;
	}
	/**
	 * 
	 * @param countryId
	 */
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	/**
	 * 
	 * @return String
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 
	 * @return String
	 */
	public String getIso() {
		return iso;
	}
	/**
	 * 
	 * @param iso
	 */
	public void setIso(String iso) {
		this.iso = iso;
	}
	/**
	 * 
	 * @return String
	 */
	public String getIso3letter() {
		return iso3letter;
	}
	/**
	 * 
	 * @param iso3letter
	 */
	public void setIso3letter(String iso3letter) {
		this.iso3letter = iso3letter;
	}
	/**
	 * 
	 * @return String
	 */
	public String getIso3numeric() {
		return iso3numeric;
	}
	/**
	 * 
	 * @param iso3numeric
	 */
	public void setIso3numeric(String iso3numeric) {
		this.iso3numeric = iso3numeric;
	}
}
