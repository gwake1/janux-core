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
 * Created on Mar 23, 2006
 */
package biz.janux.commerce;

import org.janux.bus.persistence.PersistentAbstract;

import biz.janux.people.Party;

/**
 * 
 *
 * @author David Fairchild
 *
 */
public class AbstractFormOfPayment extends PersistentAbstract implements FormOfPayment
{
	private static final long serialVersionUID = 634079964174836924L;
	private Party party;
	private int position;

	/**
	 * @return Returns the position.
	 */
	public int getPosition()
	{
		return position;
	}
	/**
	 * @param position The position to set.
	 */
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	/**
	 * @return Returns the party.
	 */
	public Party getParty()
	{
		return party;
	}
	/**
	 * @param party The party to set.
	 */
	public void setParty(Party party)
	{
		this.party = party;
	}
	
	public Object clone() 
	{
	    try 
	    {
	        FormOfPayment result = (FormOfPayment )super.clone();
	        
	        result.setId(-1);
	        result.setParty(this.getParty());
	        
	        return result;
	    } 
	    catch (CloneNotSupportedException e) 
	    {
	        return null;
	    }
	}
 
}
