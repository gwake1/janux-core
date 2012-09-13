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

package org.janux.util;

import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.StandardToStringStyle;


/**
 ***************************************************************************************************
 * Convenience class that provides alternatives to the apache lang StandardToStringStyle, and
 * exposes them as static variables
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4
 ***************************************************************************************************
 */
public class JanuxToStringStyle extends StandardToStringStyle
{
	/** instantiates a plain vanilla JanuxToStringStyle class, which uses a COMPACT format by default */
	public static final ToStringStyle COMPACT = new JanuxToStringStyle();
	
	/** 
	 * Instantiates a plain vanilla StandardToStringStyle, but sets:
	 * <ul>
	 * 	<li>useFieldNames(true)</li>
	 * 	<li>useShortClassName(true)</li>
	 * 	<li>useUseIdentityHashCode(false)</li>
	 * </ul>
	 */
	public JanuxToStringStyle() 
	{
		super();
		this.setUseFieldNames(true);
		this.setUseShortClassName(true);
		this.setUseIdentityHashCode(false);
	}

} // end class


