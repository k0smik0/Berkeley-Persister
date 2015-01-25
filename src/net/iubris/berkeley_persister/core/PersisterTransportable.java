package net.iubris.berkeley_persister.core;

import java.util.Iterator;
import java.util.Map;

import net.iubris.berkeley_persister.core.helper.base.BerkeleyDBHelper;
import net.iubris.berkeley_persister.dao.base.DAO;
import net.iubris.berkeley_persister.dao.base.exception.ExistantValueException;
import net.iubris.berkeley_persister.dao.base.exception.MismatchKeysException;
import net.iubris.berkeley_persister.dao.base.exception.NotAlreadyExistantValueException;
import net.iubris.berkeley_persister.model.TransportHolder;

import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;
import com.sleepycat.je.Transaction;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.PrimaryIndex;

public class PersisterTransportable<K, V extends TransportHolder<K>> implements DAO<K, V, V, Boolean, Boolean> {
	
	protected final BerkeleyDBHelper<K,V> berkeleyDBHelper;
	protected final Class<K> keyClass;
	protected final Class<V> valueClass;
	
	protected PrimaryIndex<K, V> primaryIndex;
	protected Transaction transaction;

	public PersisterTransportable(BerkeleyDBHelper<K, V> berkeleyDBHelper, Class<K> keyClass, Class<V> valueClass ) {
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
	
	// old true if value is correctly stored, false everywhere (it means an old value is existant, but ExistantValueException was not throwed for any reason and something went wrong - however, new value is stored)
	/**
	 * @return input value if stored was fine
	 * @throws ExistantValueException if an old value with same key is found
	 */
	@Override
	public V create(V value) throws ExistantValueException {
		/* OLD
//		K transportKey = value.transportKey();
//System.out.print("adding "+transportKey+": ");
		checkIfExistant(value.getTransportKey());
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
		 */
		boolean putNoOverwrite = primaryIndex.putNoOverwrite(value);
		if (putNoOverwrite)
			return value;
		throw new ExistantValueException();
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
	public Boolean update(K key, V value) throws MismatchKeysException, NotAlreadyExistantValueException {
		if (contains(key).equals(Boolean.TRUE)) {
			V existant = get(key);
			if (existant.getTransportKey().equals(value.getTransportKey())) {
//				primaryIndex.delete(existant.getTransportKey());
				V old = primaryIndex.put(value);
				if (old==null)
					return false;
				else {
					if (old.getTransportKey().equals(existant.getTransportKey()))
						return true;
					throw new MismatchKeysException();
				}
			} else {
				throw new MismatchKeysException();
			}
		} else {
			throw new NotAlreadyExistantValueException();
		}
	}
	
	@Override
	public Boolean update(V value) throws MismatchKeysException, NotAlreadyExistantValueException {
		K transportKey = value.getTransportKey();
		if (contains(transportKey).equals(Boolean.TRUE)) {
			V existant = get(transportKey);
			if (existant.getTransportKey().equals(value.getTransportKey())) {
//				primaryIndex.delete(existant.getTransportKey());
				V old = primaryIndex.put(value);
				if (old==null)
					return false;
				else {
					if (old.getTransportKey().equals(existant.getTransportKey()))
						return true;
					throw new MismatchKeysException();
				}
			} else {
				throw new MismatchKeysException();
			}
		} else {
			throw new NotAlreadyExistantValueException();
		}
	}
	
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
