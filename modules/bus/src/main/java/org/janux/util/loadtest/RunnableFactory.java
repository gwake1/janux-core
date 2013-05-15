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

package org.janux.util.loadtest;


/**
 ***************************************************************************************************
 * This defines an interface for a factory class that can produce Runnable instances that can be
 * used to run a multi-threaded load test; instances of a RunnableFactory can be set inside a
 * LoadRunner to run a variety of load tests, in different circumnstances
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.1 $ - $Date: 2007-06-16 01:13:26 $
 ***************************************************************************************************
 */
public interface RunnableFactory
{
	/** 
	 * this factory method returns Runnable instances that can be used for a load test; note that in a
	 * typical situation this method should return different Runnable instances on every call, for
	 * example to mimic different users interracting with the system concurrently in different ways;
	 * also note that there is nothing that limits this factory from returning different Runnable
	 * implementations to mimic different interactions with the system; for example, a class may mimic
	 * a user viewing products in an online-catalog, while another class may mimic a user placing an
	 * order from that online-catalog
	 */
	Runnable getRunnable();

} // end class


