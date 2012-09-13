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

package biz.janux.commerce.payment.util;

import com.Ostermiller.util.RandPass;

/**
 * @author Nilesh
 * <br/>
 * <br/>
 * <b>Description :</b>
 * Class to handle String randomizer functionality.<br/>
 * This functionality will be used mainly for generating UUID 
 * for credit card and the merchant account
 * */
public class StringRandomizerUtil {
	
	private static final int MAX_LENGTH = 32;
	private static final String SALT = "1G*8+i";
	
	public static String getRandomStringFor(String string){
		string += SALT; 
		char[] chars = string.toCharArray();		
		return new RandPass(chars).getPass(MAX_LENGTH);
	}
}
