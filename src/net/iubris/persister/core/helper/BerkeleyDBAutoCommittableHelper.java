package net.iubris.persister.core.helper;

import com.sleepycat.persist.model.EntityModel;

public class BerkeleyDBAutoCommittableHelper<K,V> extends AbstractBerkeleyDBHelper<K, V> {

	public BerkeleyDBAutoCommittableHelper(String corpusName, String siloName, EntityModel entityModel, BerkeleyDBHelperManager berkeleyDBHelperManager) {
		super(corpusName, siloName, true, true, entityModel, berkeleyDBHelperManager);
	}
	
	@Override
	public boolean isTransactionable() {
		return false;
	}
}