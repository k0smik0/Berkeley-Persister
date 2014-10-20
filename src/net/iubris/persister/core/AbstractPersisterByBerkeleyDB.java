package net.iubris.persister.core;

import java.util.Iterator;
import java.util.Map;

import net.iubris.persister.core.helper.BerkeleyDBHelper;
import net.iubris.persister.dao.base.DAO;
import net.iubris.persister.dao.base.exception.ExistantValueException;
import net.iubris.persister.model.transport.base.TransportHolder;

import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;
import com.sleepycat.je.Transaction;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.PrimaryIndex;

public abstract class AbstractPersisterByBerkeleyDB<K, V extends TransportHolder<K>> implements DAO<K, V, Boolean, Boolean, V> {
	
	protected final BerkeleyDBHelper<K,V> berkeleyDBHelper;
	protected final Class<K> keyClass;
	protected final Class<V> valueClass;
	
	protected PrimaryIndex<K, V> primaryIndex;
	protected Transaction transaction;

	public AbstractPersisterByBerkeleyDB(BerkeleyDBHelper<K, V> berkeleyDBHelper, Class<K> keyClass, Class<V> valueClass ) {
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

//	@Override
//	public void close() {
//		berkeleyDBHelper.closeCursors();
//	}

	/**
	 * Inserts an entity and returns null, or updates it if the primary key already exists and returns the existing entity.
	 * @throws ExistantValueException 
	 */
	/*@Override
	public Boolean create(V value) throws ExistantValueException {
		K transportKey = value.transportKey();
System.out.print("adding "+transportKey+": ");
		if (primaryIndex.contains(transportKey))
			throw new ExistantValueException();
System.out.print(" .. ");
		V putted;
		if (transaction!=null)
			putted = primaryIndex.put(transaction, value);
		else
			putted = primaryIndex.put(value);
		if (putted == null) {
System.out.println("ok");
			return true;
		}
System.out.println("exception not throwed, nor "+transportKey+"added - something went wrong");
		return false;
	};*/
	
	/**
	 * @return true if value is correctly stored, false everywhere (it means an old value is existant, but ExistantValueException was not throwed for any reason and something went wrong - however, new value is stored)  
	 * @throws ExistantValueException if an old value with same key is found
	 */
	@Override
	public Boolean create(V value) throws ExistantValueException {
//		K transportKey = value.transportKey();
//System.out.print("adding "+transportKey+": ");
		checkIfExistant(value.transportKey());
//System.out.print(" .. ");
//		V putted;
//		if (transaction!=null)
//			putted = primaryIndex.put(transaction, value);
//		else
//			putted = primaryIndex.put(value);
		// if transaction field is null, autotransactionable is used
		V putted = primaryIndex.put(transaction, value);
		if (putted == null) {
//System.out.println("ok");
			return true;
		}
//System.out.println("exception not throwed, nor "+transportKey+"added - something went wrong");
		//if here, something was wrong
		return false;
	};
	
	protected void checkIfExistant(K key) throws ExistantValueException {
		if (primaryIndex.contains(key))
			throw new ExistantValueException();
	}
	@Override
	public Boolean remove(K key) {
		return primaryIndex.delete(transaction,key);
	}

	@Override
	public abstract V update(V value);
	
	@Override
	public V get(K key) {
		return primaryIndex.get(key);
	}

	@Override
	public Boolean contains(K key) {
		/*System.out.println(key);
		boolean contains = primaryIndex.contains(key);
		return contains;*/
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
//		Transaction txn = environment.beginTransaction(null, null);
		
		Database database = primaryIndex.getDatabase();
		String databaseName = database.getDatabaseName();
		database.close();
//		environment.removeDatabase(txn, databaseName);
		environment.removeDatabase(null, databaseName);
//		txn.commit();
		
		// recreate
		berkeleyDBHelper.openStorage();
		this.primaryIndex = berkeleyDBHelper.getPrimaryIndex(keyClass, valueClass);
	}
}
