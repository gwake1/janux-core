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
 * Base interface to represent any sort of class that takes an Object as it input and returns a
 * String as output; 
 *
 * Derived classes that extend ObjectFormatterAbstract, only need to implement 
 * the {@link #format(Object)} as that class provides a convenience implementation of
 * {@link Processor#process(InputStream, OutputStream)} that turns the
 * 'in' InputStream into a String, calls {@link ObjectFormatterAbstract#format(Object)},
 * and pipes the String returned to the 'out' OutputStream.
 *
 * @see ObjectFormatterAbstract
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since version 0.2.04 - Jun 20, 2007
 ***************************************************************************************************
 */
public interface ObjectFormatter extends Processor
{
	String format(Object o);

} // end class


