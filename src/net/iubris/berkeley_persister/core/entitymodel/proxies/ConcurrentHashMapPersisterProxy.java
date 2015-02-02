/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (ConcurrentHashMapPersisterProxy.java) is part of BerkeleyPersister.
 * 
 *     ConcurrentHashMapPersisterProxy.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     ConcurrentHashMapPersisterProxy.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.berkeley_persister.core.entitymodel.proxies;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sleepycat.persist.model.Persistent;
import com.sleepycat.persist.model.PersistentProxy;

@Persistent(proxyFor=ConcurrentHashMap.class)
public class ConcurrentHashMapPersisterProxy<K,V> implements PersistentProxy<ConcurrentHashMap<K,V>> {

	private final Map<K,V> map = new HashMap<K,V>();
	
	@Override
	public ConcurrentHashMap<K,V> convertProxy() {
		ConcurrentHashMap<K,V> concurrentHashMap = new ConcurrentHashMap<K,V>();
		concurrentHashMap.putAll(map);
		return concurrentHashMap;
	}

	@Override
	public void initializeProxy(ConcurrentHashMap<K,V> map) {
		this.map.putAll(map);
	}

}
