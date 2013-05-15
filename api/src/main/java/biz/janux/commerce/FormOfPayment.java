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

/*
 * Created on Mar 17, 2006
 */
package biz.janux.commerce;

import java.io.Serializable;

import org.janux.bus.persistence.Persistent;

import biz.janux.people.Party;

/**
 * This interface is used as a marker to identify a form of payment
 *
 * @author David Fairchild
 *
 */
public interface FormOfPayment extends Serializable, Persistent, Cloneable
{

	/**
	 * @return Returns the position.
	 */
	int getPosition();
	
	/**
	 * @param position The position to set.
	 */
	void setPosition(int position);
	
	/**
	 * @return Returns the party.
	 */
	Party getParty();
	/**
	 * @param party The party to set.
	 */
	void setParty(Party party);
	
	Object clone() throws CloneNotSupportedException;
}
