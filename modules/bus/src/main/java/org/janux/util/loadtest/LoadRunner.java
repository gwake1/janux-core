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

import java.util.List;

/**
 ***************************************************************************************************
 * Interface for classes that define the manner in which multi-threaded Load Tests are conducted;
 * this class defines a startThreads() method that defines how multiple threads are spawned, started
 * and stopped, during a load test. This class expects to be provided with a RunnableFactory, a
 * factory class that can be used by the startThreads() to instantiate the Runnable classes to be
 * run in each thread. 
 * <p>Sub-classes of LoadRunner tend to be generic across business domains, as their purpose is to
 * orchestrate the lifecycle of the threads. On the other hand, the RunnableFactory class are
 * expected to mimic actions specific to the application to be tested, for example, a user
 * interracting with an online shopping application.</p>
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.1 $ - $Date: 2007-06-16 01:13:26 $
 ***************************************************************************************************
 */
public interface LoadRunner
{
	RunnableFactory getRunnableFactory();

	void setRunnableFactory(RunnableFactory runnableFactory);

	int getNumThreads();

	void setNumThreads(int numThreads);

	String getThreadNamePrefix();
	
	void setThreadNamePrefix(String threadNamePrefix);
	
	/** 
	 * Determines the manner in which the threads are created, ramped up and ended in a given load
	 * test.  See SimpleLoadRunner for a simple implementation that creates a user-specified N number
	 * of threads.
	 *
	 * @see SimpleLoadRunner
	 */
	void startThreads() throws Exception;

	/** returns a list of the Runnable classes that the LoadRunner is running */
	List<Runnable> getRunnableList();

	/** returns a list of the Threads that a LoadRunner is running */
	List<Thread> getThreadList();
	
} // end class


