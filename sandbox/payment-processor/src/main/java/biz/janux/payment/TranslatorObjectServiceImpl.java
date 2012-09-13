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

package biz.janux.payment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TranslatorObjectServiceImpl implements TranslatorObjectService {

	public Authorization translate(Authorization authorization) {
		if (authorization!=null)
			authorization.setModifications((List)translate(authorization.getModifications()));
		
		return authorization;
	}
	
	public AuthorizationHotel translate(AuthorizationHotel authorizationHotel) {
		if (authorizationHotel!=null)
			authorizationHotel = (AuthorizationHotel) translate((Authorization)authorizationHotel);
		
		return authorizationHotel;
	}

	public List<Object> translate(List<Object> list) {
		
		List listJava = new ArrayList<Object>();
		for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			listJava.add(translate(object));
		}
		return listJava;
	}

	public Object translate(Object object) {
		if (object instanceof List)
			return translate((List<Object>)object);
		/*if (object instanceof SettlementItemHotel)
			return translate((SettlementItemHotel)object);
		if (object instanceof Settlement)
			return translate((Settlement)object);
		if (object instanceof Authorization)
			return translate((Authorization)object);
		if (object instanceof AuthorizationHotel)
			return translate((AuthorizationHotel)object);*/
		
		return object;
	}

	public SettlementItemHotel translate(SettlementItemHotel settlementItemHotel) {
		if (settlementItemHotel!=null)
		{
			if (settlementItemHotel.getSettlement()!=null)
				settlementItemHotel.getSettlement().setSettlementItems(null);

			settlementItemHotel.setAuthorization(translate(settlementItemHotel.getAuthorization()));
		}
		return settlementItemHotel;
	}

	public Settlement translate(Settlement settlement) {
		if (settlement!=null){
			settlement.setSettlementItems((List)translate(settlement.getSettlementItems()));
			
			/**
			 * avoid lazy exception
			 */
			for (Iterator iterator = settlement.getSettlementItems().iterator(); iterator.hasNext();) {
				SettlementItemHotel settlementItemHotel = (SettlementItemHotel) iterator.next();
				if (settlementItemHotel.getAuthorization()!=null)
					settlementItemHotel.setAuthorization(null);
			}
		}
		return settlement;
	}
}
