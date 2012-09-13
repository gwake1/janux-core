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

package biz.janux.people.service;

import java.util.List;

import biz.janux.people.PersonDaoGeneric;
import biz.janux.people.PersonImpl;

import com.trg.search.ISearch;

/**
 * 
 * @author <a href="mailto:moradaniel@janux.org">Daniel Mora</a>
 *
 */

public class PersonServiceImplGeneric implements PersonService<PersonImpl,ISearch> {
	
	protected PersonDaoGeneric<PersonImpl> personDaoGeneric;
	
	public void setPersonDaoGeneric(PersonDaoGeneric<PersonImpl> personDao) {
		this.personDaoGeneric = personDao;
	}
	
	public List<PersonImpl> find(ISearch search){
		return personDaoGeneric.findByCriteria(search);
	}

	public int count(ISearch search){
		return this.personDaoGeneric.count(search);
	}
	
	public PersonImpl find(Integer idPerson){
		return (PersonImpl) personDaoGeneric.load(idPerson);
	}
	
	public void delete(Integer idPerson){
		personDaoGeneric.delete(find(idPerson));
	}


	public Integer save(PersonImpl person){
		this.personDaoGeneric.save(person);
		return person.getId();
	}
	
	public Integer saveOrUpdate(PersonImpl person){
		this.personDaoGeneric.saveOrUpdate(person);
		return person.getId();
	}
	

	
	public void save(List<PersonImpl> Persons){
		//TODO implement this
	}

}

