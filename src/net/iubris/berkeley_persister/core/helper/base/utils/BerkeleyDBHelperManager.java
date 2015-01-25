package net.iubris.berkeley_persister.core.helper.base.utils;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;

import net.iubris.berkeley_persister.core.helper.base.BerkeleyDBHelper;

@Singleton
public class BerkeleyDBHelperManager {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final Set<BerkeleyDBHelper> berkeleyDBHelpers = new HashSet();

	@SuppressWarnings("rawtypes")
	public void add(BerkeleyDBHelper berkeleyDBHelper) {
		berkeleyDBHelpers.add(berkeleyDBHelper);
	}
	
	@SuppressWarnings("rawtypes")
	public void close() {
		for (BerkeleyDBHelper berkeleyDBHelper: berkeleyDBHelpers) {
			berkeleyDBHelper.closeStorage();
		}
	}
}
