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

package biz.janux.commerce.payment.interfaces.response.codes;
/**
 * 
 * @author Nilesh
 * </br>
 * Interface for Error response codes
 *
 */
public interface ResponseCodes {
	/**
	 * Error Response Codes
	 */
	public String ERROR_BLOCKEDTERMINAL = "B";

	public String ERROR_CARDTYPE = "C";

	public String ERROR_DEVICE = "D";

	public String ERROR_TRANSMISSION = "T";

	public String ERROR_UNKNOWN = "U";

	public String ERROR_ROUTNING = "V";
	
	public String STATUS_OK = "OK";
	
	public String STATUS_ERROR = "ERROR";

}
