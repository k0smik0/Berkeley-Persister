/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (DAO.java) is part of BerkeleyPersister.
 * 
 *     DAO.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     DAO.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.berkeley_persister.dao.base;

import java.util.Iterator;
import java.util.Map;

import net.iubris.berkeley_persister.dao.base.exception.ExistantValueException;
import net.iubris.berkeley_persister.dao.base.exception.UpdateException;
import net.iubris.berkeley_persister.io.Committable;


/**
 * 
 * @author k0smik0
 *
 * @param <K> K key type
 * @param <V> V value type
 * @param <CR> CR create operation return type
 * @param <RR> RR remove operation return type 
 * @param <UR> UR update operation return type
 */
public interface DAO<K, V, CR, RR, UR> extends Committable {

	public CR create(V value) throws ExistantValueException;
	public RR remove(K key);
	public UR update(K key, V value) throws UpdateException;
	public UR update(V value) throws UpdateException;
	public V get(K key);
	
	Boolean contains(K key);
	
	public Iterator<K> keysIterator();
	public Iterator<V> valuesIterator();
	public Map<K,V> asMap();
	
	public int size();
	public boolean isEmpty();

	void clear();
}
