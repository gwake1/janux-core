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

import org.janux.bus.search.SearchCriteria;

import biz.janux.people.Person;

/**
 * 
 * @author <a href="mailto:moradaniel@janux.org">Daniel Mora</a>
 *
 */
public interface PersonService<P extends Person, S extends SearchCriteria> {
	
	public List<P> find(S search);
	public int count(S search);
	public P find(Integer idPerson);
	public void delete(Integer idPerson);
	public Integer save(P person);
	public Integer saveOrUpdate(P person);
	public void save(List<P> Persons);

}