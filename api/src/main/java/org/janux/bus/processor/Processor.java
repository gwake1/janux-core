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

import java.io.InputStream;
import java.io.OutputStream;

/**
 ***************************************************************************************************
 * Base interface to represent any sort of class that takes an input and returns an output; mostly
 * used to define classes that perform transformations to messages, whatever form those messages may
 * take (Objects, Strings, bytes, etc...) or to satisfy any part of a Request/Response process or
 * other Messaging process.
 *
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since version 0.2.03 - Jun 20, 2007
 ***************************************************************************************************
 */
public interface Processor 
{
	/** @throws RuntimeException as necessary */
	void process(InputStream in, OutputStream out);
}
