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

package biz.janux.geography.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

/**
 * This class is an advice that throws a {@link RuntimeException} after a method executes.
 * This is useful for advising transactional methods when testing them. 
 * @author   <a href="mailto:moradaniel@janux.org">Daniel Mora</a>
 */
public class RuntimeExceptionAfterReturningAdvice implements AfterReturningAdvice {

	protected Log log = LogFactory.getLog(this.getClass());
	
	public void afterReturning(Object returnValue, Method method, Object[] args,Object target) throws Throwable {
		if (log.isDebugEnabled())
			log.debug("Throwing RuntimeException after method: " + method.getName());
		throw new RuntimeException();
		}
	}