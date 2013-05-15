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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.io.OutputStream;


/**
 ***************************************************************************************************
 * Base implementation of the ObjectFormatter interface; will implement a template Processor.process method
 * at some point in the future, currently throws an UnsupportedOperationException
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.2 $ - $Date: 2007-12-27 00:51:17 $
 ***************************************************************************************************
 */
public abstract class ObjectFormatterAbstract implements ObjectFormatter
{
	protected transient Log log = LogFactory.getLog(this.getClass());

	public void process(InputStream in, OutputStream out) 
	{
		String msg = "process(in,out) not yet implemented"; 
		log.error(msg);
		throw new UnsupportedOperationException(msg);
	}

	public abstract String format(Object o);
	

} // end class
