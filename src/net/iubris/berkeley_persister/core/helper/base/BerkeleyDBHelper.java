package net.iubris.berkeley_persister.core.helper.base;

import com.sleepycat.je.Environment;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.PrimaryIndex;

public interface BerkeleyDBHelper<K,V> {

	boolean isTransactionable();
	void closeStorage();
	Environment getEnvironment();
	void closeCursors();
	void maintainsKeysCursor(EntityCursor<K> cursor);
	void maintainsValuesCursor(EntityCursor<V> cursor);
	void openStorage();
	PrimaryIndex<K, V> getPrimaryIndex(Class<K> keyClass, Class<V> valueClass);
}
