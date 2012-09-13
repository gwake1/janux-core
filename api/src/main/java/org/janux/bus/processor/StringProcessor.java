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

package org.janux.bus.processor;


/**
 ***************************************************************************************************
 * Base interface to represent any sort of class that takes a String as input and returns a String
 * as output; mostly used to define classes that perform transformations to String messages,  or to
 * satisfy any part of a Request/Response process or other messaging process that uses String
 * Messages. 
 *
 * Derived classes that extend StringProcessorAbstract, only need to implement 
 * the {@link #process(String)} as that class provides a convenience implementation of
 * {@link Processor#process(InputStream, OutputStream)} that turns the
 * 'in' InputStream into a String, calls {@link StringProcessorAbstract#process(String)},
 * and pipes the String returned to the 'out' OutputStream.
 *
 * @see StringProcessorAbstract
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since version 0.2.03 - Jun 20, 2007
 ***************************************************************************************************
 */
public interface StringProcessor extends Processor
{
	String process(String in);
}
