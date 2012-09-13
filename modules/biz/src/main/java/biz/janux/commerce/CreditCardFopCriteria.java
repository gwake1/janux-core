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

package biz.janux.commerce;

import java.util.Date;

/**
 * Preliminary Criteria object for credit card searches.  This will eventually be replaced with the
 * SearchCriteria interface and implementation that will be part of the org.janux.bus.search
 * package.
 * 
 * @author <a href="mailto:alberto.buffagni@janux.org">Alberto Buffagni</a>
 * @since 0.4
 */
public class CreditCardFopCriteria 
{
	/**
	 * indicates the date to consider if a credit card has to be masked
	 */
	Date limitDate;
	
	/**
	 * Set the index of the first object to retrieve.
	 */
	Integer firstResult;
	

	/**
	 * Set the maximum number of object to retrieve. 
	 */
	Integer maxResults;
	
	Boolean masked;
	Boolean encrypted;


	public Integer getFirstResult() {
		return firstResult;
	}


	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}


	public Integer getMaxResults() {
		return maxResults;
	}


	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}


	public Boolean isMasked() {
		return masked;
	}


	public void setMasked(Boolean masked) {
		this.masked = masked;
	}


	public Boolean isEncrypted() {
		return encrypted;
	}


	public void setEncrypted(Boolean encrypted) {
		this.encrypted = encrypted;
	}

}
