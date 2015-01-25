package net.iubris.berkeley_persister.core.helper;

import net.iubris.berkeley_persister.core.helper.base.AbstractBerkeleyDBHelper;
import net.iubris.berkeley_persister.core.helper.base.utils.BerkeleyDBHelperManager;

import com.sleepycat.persist.model.EntityModel;



public class BerkeleyDBTransactionableHelper<K,V> extends AbstractBerkeleyDBHelper<K, V> {

	public BerkeleyDBTransactionableHelper(String corpusName, String siloName, EntityModel entityModel, BerkeleyDBHelperManager berkeleyDBHelperManager) {
		super(corpusName, siloName, true, true, entityModel, berkeleyDBHelperManager);
	}

	@Override
	public boolean isTransactionable() {
		return true;
	}
}
