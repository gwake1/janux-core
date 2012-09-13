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
 * Base interface to represent any sort of class that takes a String as input and returns an Object
 * as output; mostly used to define classes that parse/unmarshall Strings into objects
 *
 * Derived classes that extend StringParserAbstract (TODO), only need to implement the 
 * {@link #parse(String in)} as that class provides a convenience implementation of 
 * {@link Processor#process} that turns the 'in' InputStream into a String, calls 
 * {@link StringProcessorAbstract#process(String in)}, and returns on ObjectOutputStream via the the
 * out OutputStream.
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 ***************************************************************************************************
 */
public interface StringParser extends Processor
{
	Object parse(String in);
}
