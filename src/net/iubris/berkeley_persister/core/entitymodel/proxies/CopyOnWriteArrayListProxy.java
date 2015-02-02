/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (CopyOnWriteArrayListProxy.java) is part of BerkeleyPersister.
 * 
 *     CopyOnWriteArrayListProxy.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     CopyOnWriteArrayListProxy.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
