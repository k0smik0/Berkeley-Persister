package net.iubris.berkeley_persister.core.entitymodel.proxies;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.sleepycat.persist.model.Persistent;
import com.sleepycat.persist.model.PersistentProxy;

@Persistent(proxyFor=CopyOnWriteArrayList.class)
public class CopyOnWriteArrayListProxy<E> implements PersistentProxy<CopyOnWriteArrayList<E>> {

	private final List<E> list = new ArrayList<E>();
	
	@Override
	public CopyOnWriteArrayList<E> convertProxy() {
		CopyOnWriteArrayList<E> list = new CopyOnWriteArrayList<E>();
		list.addAll(this.list);
		return list;
	}

	@Override
	public void initializeProxy(CopyOnWriteArrayList<E> list) {
		this.list.addAll(list);
	}

}
