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

package api.org.janux.bus.service;

import java.io.Serializable;
import java.util.List;

import org.janux.bus.persistence.DataAccessException;
import org.janux.bus.search.SearchCriteria;

/**
 * 
 * @author albertobuffagni@gmail.com
 *
 * @param <T>
 * @param <ID>
 * @param <S>
 */
public interface GenericService<T, ID extends Serializable, S extends SearchCriteria> {

	public int count(S searchCriteria);
	public void delete(T persistentObject);
	public List<T> findAll(S searchCriteria);
	public T find(S searchCriteria);
	public T save(T persistentObject);
	public T load(ID id);
	public T load(String uuid);

}
