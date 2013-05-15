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

package biz.janux.test;

import org.janux.bus.persistence.TransactionalTestAbstract;

/**
 * This class can be used for all transactional tests in the biz.janux module; it merely extends
 * {@link org.janux.bus.persistence.TransactionalTestAbstract} by specifying the location of the
 * Application Context files used to configure this module
 *
 * @author <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 *
 * @version  $Revision: 1.4 $ - $Date: 2007-03-06 16:03:33 $
 */
public abstract class TransactionalBizTestAbstract extends TransactionalTestAbstract
{
	protected String[] getConfigLocations(){
		return new String[] {
			"classpath:ApplicationContext.xml",
		};
	}
	/*
			"classpath:DatabaseContext.xml",
			"classpath:HibernateContext.xml",
			"classpath:SecurityContext.xml",
			"classpath:BizHibernateMappings.xml",
			"classpath:BizTransactionContext.xml",
			"classpath:PartyContext.xml",
			"classpath:GeographyContext.xml",
			"classpath:CommerceContext.xml"
	 */

	public TransactionalBizTestAbstract() {
		super();
	}

	/** used to provide the standard Test String constructor */
	public TransactionalBizTestAbstract(String name) {
		super(name);
	}

}
