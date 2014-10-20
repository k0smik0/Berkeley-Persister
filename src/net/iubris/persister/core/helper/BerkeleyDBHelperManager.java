package net.iubris.persister.core.helper;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;

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
