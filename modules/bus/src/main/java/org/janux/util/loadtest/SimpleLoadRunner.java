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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.janux.util.loadtest.ui.Displayable;


/**
 ***************************************************************************************************
 * Simple load runner that spawns N number of threads, at configurable random intervals
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @version $Revision: 1.2 $ - $Date: 2007-06-19 05:32:52 $
 ***************************************************************************************************
 */
public class SimpleLoadRunner implements LoadRunner
{
	Log log = LogFactory.getLog(this.getClass());

	private String threadNamePrefix = "Thread";
	private int numThreads = 5;
	private RunnableFactory runnableFactory;
	private List<Runnable> runnableList;
	private List<Thread>   threadList;
	
	public SimpleLoadRunner() 
	{
	}

	public RunnableFactory getRunnableFactory()
	{
		return runnableFactory;
	}

	public void setRunnableFactory(RunnableFactory runnableFactory)
	{
		this.runnableFactory = runnableFactory;
	}

	public int getNumThreads()
	{
		return numThreads;
	}

	public void setNumThreads(int numThreads)
	{
		this.numThreads = numThreads;
	}

	public String getThreadNamePrefix()
	{
		return threadNamePrefix;
	}

	public void setThreadNamePrefix(String threadNamePrefix)
	{
		this.threadNamePrefix = threadNamePrefix;
	}

	public List<Runnable> getRunnableList()
	{
		return runnableList;
	}
	
	public List<Thread> getThreadList()
	{
		return threadList;
	}
	
	public void startThreads() throws Exception
	{
		this.runnableList = new ArrayList<Runnable>();
		this.threadList   = new ArrayList<Thread>();
		
		for (int i = 0; i < this.numThreads; i++)
		{
			final String sThreadName = threadNamePrefix + i;
			final Runnable runnable = this.runnableFactory.getRunnable();
			if (runnable instanceof Displayable)
			{
				((Displayable )runnable).setThreadName(sThreadName);
			}
			final Thread thread = new Thread(runnable, sThreadName);
			thread.setPriority(Thread.NORM_PRIORITY);
			thread.setDaemon(false);
			thread.start();
			
			this.runnableList.add(runnable);
			this.threadList.add(thread);
		}

		// cause the main execution thread to wait for all the threads to terminate
		for (Thread t: this.getThreadList()) t.join();
	}

} // end class LoadRunner
