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
