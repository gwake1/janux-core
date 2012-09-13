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
 * String as output; this is a version equivalent to ObjectFormatter, but which uses generics to
 * type the object being formatted
 *
 * @see StringProcessorAbstract
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since version 0.4.02 - Mar 15, 2011
 ***************************************************************************************************
 */
public interface ObjectFormatterGeneric<T> extends Processor
{
	String format(T o);

} // end class


