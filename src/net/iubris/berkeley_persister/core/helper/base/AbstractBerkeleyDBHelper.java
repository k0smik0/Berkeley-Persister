package net.iubris.berkeley_persister.core.helper.base;

import java.util.HashSet;
import java.util.Set;

import net.iubris.berkeley_persister.core.helper.base.utils.BerkeleyDBHelperManager;
import net.iubris.berkeley_persister.core.helper.base.utils.BerkeleyDBUtils;

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
	
	public AbstractBerkeleyDBHelper(String corpusName, String siloName, boolean allowCreate, boolean transactional, EntityModel entityModel, BerkeleyDBHelperManager berkeleyDBHelperManager) {
		this.corpusName = corpusName;
		this.siloName = siloName;
		
		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setAllowCreate(allowCreate);
		storeConfig.setTransactional(transactional);
//System.out.println(this.getClass().getSimpleName()+": "+entityModel);
		storeConfig.setModel(entityModel);
		this.storeConfig = storeConfig;
		
		EnvironmentConfig environmentConfig = new EnvironmentConfig();
		environmentConfig.setAllowCreate(allowCreate);
		environmentConfig.setTransactional(transactional);
		this.environmentConfig = environmentConfig;
		
//		registerModels();
		
		this.environment = new Environment(BerkeleyDBUtils.getSiloDir(corpusName, siloName) , environmentConfig);
		this.entityStore = new EntityStore(environment, siloName, storeConfig);
	
//		if (transactional)
//			environmentConfig.setLocking(true);

		openStorage();
		
		berkeleyDBHelperManager.add(this);
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
