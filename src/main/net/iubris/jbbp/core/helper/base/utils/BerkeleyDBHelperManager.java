/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (BerkeleyDBHelperManager.java) is part of BerkeleyPersister.
 * 
 *     BerkeleyDBHelperManager.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     BerkeleyDBHelperManager.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.jbbp.core.helper.base.utils;

import java.util.HashSet;
import java.util.Set;

import net.iubris.jbbp.core.helper.base.BerkeleyDBHelper;

/**
 * singleton
 */
public class BerkeleyDBHelperManager {
	
	private static BerkeleyDBHelperManager instance;
	private BerkeleyDBHelperManager() {};
	public static BerkeleyDBHelperManager getInstance() {
		if (instance==null)
			instance = new BerkeleyDBHelperManager();
		return instance;
	}

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
