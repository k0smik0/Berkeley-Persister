package net.iubris.berkeley_persister.core;

import java.util.Iterator;
import java.util.Map;

import net.iubris.berkeley_persister.core.helper.base.BerkeleyDBHelper;
import net.iubris.berkeley_persister.dao.base.DAO;
import net.iubris.berkeley_persister.dao.base.exception.ExistantValueException;
import net.iubris.berkeley_persister.dao.base.exception.UpdateException;

import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;
import com.sleepycat.je.Transaction;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.PrimaryIndex;

public class Persister<K, V> implements DAO<K, V, V, Boolean, Boolean> {
	
	protected final BerkeleyDBHelper<K,V> berkeleyDBHelper;
	protected final Class<K> keyClass;
	protected final Class<V> valueClass;
	
	protected PrimaryIndex<K, V> primaryIndex;
	protected Transaction transaction;

	public Persister(BerkeleyDBHelper<K, V> berkeleyDBHelper, Class<K> keyClass, Class<V> valueClass ) {
		this.berkeleyDBHelper = berkeleyDBHelper;
		this.keyClass = keyClass;
		this.valueClass = valueClass;
		this.primaryIndex = berkeleyDBHelper.getPrimaryIndex(keyClass, valueClass);
	}

	@Override
	public void beginTransaction() {
		if ( berkeleyDBHelper.isTransactionable() )
			transaction = this.berkeleyDBHelper.getEnvironment().beginTransaction(null,null);
	}

	@Override
	public void commit() {
		if (transaction!=null)
			transaction.commit();
	}

	// old true if value is correctly stored, false everywhere (it means an old value is existant, but ExistantValueException was not throwed for any reason and something went wrong - however, new value is stored)
	/**
	 * @return input value if stored was fine
	 */
	// * @throws ExistantValueException if an old value with same key is found
	@Override
	public V create(V value) /*throws ExistantValueException*/ {
		V putted = primaryIndex.put(value);
//		if (putNoOverwrite)
//			return value;
//		throw new ExistantValueException();
		return putted;
	}
	
	protected void checkIfExistant(K key) throws ExistantValueException {
		if (primaryIndex.contains(key))
			throw new ExistantValueException();
	}
	@Override
	public Boolean remove(K key) {
		return primaryIndex.delete(transaction,key);
	}

	@Override
	public Boolean update(K key, V value) {
		V put = primaryIndex.put(value);
		if (put==null)
			return false;
		return true;
	}
	@Override
	public Boolean update(V value) throws UpdateException {
		V updated = primaryIndex.put(value);
		if (updated==null)
			return false;
		return true;
	}
	
	@Override
	public V get(K key) {
		return primaryIndex.get(key);
	}

	@Override
	public Boolean contains(K key) {
		return primaryIndex.contains(key);
	}

	@Override
	public Iterator<K> keysIterator() {
		EntityCursor<K> keys = primaryIndex.keys();
		berkeleyDBHelper.maintainsKeysCursor(keys);
		return keys.iterator();
	}

	@Override
	public Iterator<V> valuesIterator() {
		EntityCursor<V> entities = primaryIndex.entities();
		berkeleyDBHelper.maintainsValuesCursor(entities);
		return entities.iterator();
	}

	@Override
	public Map<K, V> asMap() {
		return primaryIndex.map();
	}

	@Override
	public int size() {
		return (int) primaryIndex.count();
	}

	@Override
	public boolean isEmpty() {
		return (size()==0);
	}

	@Override
	public void clear() {
		Environment environment = berkeleyDBHelper.getEnvironment();
		
		Database database = primaryIndex.getDatabase();
		String databaseName = database.getDatabaseName();
		database.close();
		environment.removeDatabase(null, databaseName);

		// recreate
		berkeleyDBHelper.openStorage();
		this.primaryIndex = berkeleyDBHelper.getPrimaryIndex(keyClass, valueClass);
	}
}