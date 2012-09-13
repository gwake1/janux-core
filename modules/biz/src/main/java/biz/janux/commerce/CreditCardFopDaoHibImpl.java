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

package biz.janux.commerce;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.janux.bus.persistence.DataAccessHibImplAbstract;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author <a href="mailto:alberto.buffagni@janux.org">Alberto Buffagni</a>
 * @since 0.4
 */
public class CreditCardFopDaoHibImpl  extends DataAccessHibImplAbstract  implements CreditCardFopDao {

	public List<CreditCardFop> findAll(CreditCardFopCriteria creditCardFopCriteria) throws Exception 
	{
		final DetachedCriteria criteria = DetachedCriteria.forClass(CreditCardFop.class);
		
		if (creditCardFopCriteria!=null)
		{
			if (creditCardFopCriteria.isEncrypted()!=null)
				criteria.add(Restrictions.eq("encrypted",creditCardFopCriteria.isEncrypted()));	
			if (creditCardFopCriteria.isMasked()!=null)
				criteria.add(Restrictions.eq("masked",creditCardFopCriteria.isMasked()));
		}
		
		final HibernateTemplate template = getHibernateTemplate();
		final List<CreditCardFop> list;
		
		if (creditCardFopCriteria!=null && creditCardFopCriteria.firstResult!=null && creditCardFopCriteria.maxResults!=null)
			list = (List<CreditCardFop>) template.findByCriteria(criteria,creditCardFopCriteria.firstResult,creditCardFopCriteria.maxResults);
		else
			list = (List<CreditCardFop>) template.findByCriteria(criteria);
		
		return list;
	}
}
