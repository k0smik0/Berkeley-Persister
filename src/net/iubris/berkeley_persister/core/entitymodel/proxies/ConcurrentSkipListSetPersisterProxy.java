package net.iubris.berkeley_persister.core.entitymodel.proxies;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import com.sleepycat.persist.model.Persistent;
import com.sleepycat.persist.model.PersistentProxy;

@Persistent(proxyFor=ConcurrentSkipListSet.class)
public class ConcurrentSkipListSetPersisterProxy<E> implements PersistentProxy<ConcurrentSkipListSet<E>> {

	private final Set<E> set = new HashSet<E>();
	
	@Override
	public ConcurrentSkipListSet<E> convertProxy() {
		ConcurrentSkipListSet<E> concurrentSkipListSet = new ConcurrentSkipListSet<E>();
		concurrentSkipListSet.addAll(set);
		return concurrentSkipListSet;
	}

	@Override
	public void initializeProxy(ConcurrentSkipListSet<E> set) {
		this.set.addAll(set);
	}

}
