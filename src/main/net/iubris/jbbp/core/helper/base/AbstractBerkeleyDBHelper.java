/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (AbstractBerkeleyDBHelper.java) is part of BerkeleyPersister.
 * 
 *     AbstractBerkeleyDBHelper.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     AbstractBerkeleyDBHelper.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.jbbp.core.helper.base;

import java.util.HashSet;
import java.util.Set;

import net.iubris.jbbp.core.helper.base.utils.BerkeleyDBHelperManager;
import net.iubris.jbbp.core.helper.base.utils.BerkeleyDBUtils;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.StoreConfig;
import com.sleepycat.persist.model.EntityModel;

public abstract class AbstractBerkeleyDBHelper<K, V> implements BerkeleyDBHelper<K,V> {

	
	protected final Set<EntityCursor<K>> keysCursors = new HashSet<>();
	protected final Set<EntityCursor<V>> valuesCursors = new HashSet<>();
	protected final String corpusName;
	protected final String siloName;
	
	protected final StoreConfig storeConfig;
	protected final EnvironmentConfig environmentConfig;

	protected final Environment environment;
	protected final EntityStore entityStore;
	
	protected boolean storageOpened = false;

	/**
	 * 
	 * @param corpusName tipically, a directory within you want storage data
	 * @param siloName since BerkeleyDB doesn't have tables, you have to specify a Silo for each data group
	 * @param allowAutoCreate autocreate if value not exists
	 * @param isTransactional do you want transactions?
	 * @param entityModel
	 * @param berkeleyDBHelperManager
	 */
	public AbstractBerkeleyDBHelper(String corpusName, String siloName, boolean allowAutoCreate, boolean isTransactional, EntityModel entityModel/*, BerkeleyDBHelperManager berkeleyDBHelperManager*/) {
		this.corpusName = corpusName;
		this.siloName = siloName;
		
		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setAllowCreate(allowAutoCreate);
		storeConfig.setTransactional(isTransactional);
//System.out.println(this.getClass().getSimpleName()+": "+entityModel);
		storeConfig.setModel(entityModel);
		this.storeConfig = storeConfig;
		
		EnvironmentConfig environmentConfig = new EnvironmentConfig();
		environmentConfig.setAllowCreate(allowAutoCreate);
		environmentConfig.setTransactional(isTransactional);
		this.environmentConfig = environmentConfig;
		
//		registerModels();
		
		this.environment = new Environment(BerkeleyDBUtils.getSiloDir(corpusName, siloName) , environmentConfig);
		this.entityStore = new EntityStore(environment, siloName, storeConfig);
	
//		if (transactional)
//			environmentConfig.setLocking(true);

		openStorage();
		
//		berkeleyDBHelperManager.add(this); // old
		BerkeleyDBHelperManager.getInstance().add(this);
	}
	
//	public void registerModels() {
//		EntityM
//	}
	
	@Override
	public void openStorage() {
//		environment = new Environment(BerkeleyDBUtils.getSiloDir(corpusName, siloName) , environmentConfig);
//		entityStore = new EntityStore(environment, siloName, storeConfig);
		storageOpened = true;
	}
	
	@Override
	public abstract boolean isTransactionable();
	
	/**
	 * do not use
	 */
	/*@Deprecated
	public void clearDatabase() {
		Transaction txn = null;
		if (isTransactionable())
			txn = environment.beginTransaction(null, null);
		String prefix = "persist#" + entityStore.getStoreName() + "#";
		entityStore.close();
		for (String dbName : environment.getDatabaseNames()) {
			if (dbName.startsWith(prefix)) {
				environment.removeDatabase(txn, dbName);
			}
		}
		if (txn!=null)
			txn.commit();
	}*/

	@Override
	public Environment getEnvironment() {
		return environment;
	}
	
	/*public String getStoreName() {
		return siloName;
	}
	public StoreConfig getStoreConfig() {
		return storeConfig;
	}*/
	
	@Override
	public void maintainsKeysCursor(EntityCursor<K> cursor) {
		keysCursors.add(cursor);
	}
	@Override
	public void maintainsValuesCursor(EntityCursor<V> cursor) {
		valuesCursors.add(cursor);
	}
	
	@Override
	public void closeCursors() {
		for (EntityCursor<K> kc : keysCursors) {
			kc.close();
		}
		for (EntityCursor<V> kc : valuesCursors) {
			kc.close();
		}		
	}
	
	@Override
	public void closeStorage() {
//System.out.println(this+" closing");
		closeCursors();
		entityStore.close();
		environment.close();
		storageOpened = false;
	}
	
	public boolean isStorageOpened() {
		return storageOpened;
	}
	
	@Override
	public PrimaryIndex<K, V> getPrimaryIndex(Class<K> keyClass, Class<V> valueClass) {
		return entityStore.getPrimaryIndex(keyClass, valueClass);
	}
}
