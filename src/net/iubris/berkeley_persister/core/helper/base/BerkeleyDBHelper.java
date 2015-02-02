/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (BerkeleyDBHelper.java) is part of BerkeleyPersister.
 * 
 *     BerkeleyDBHelper.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     BerkeleyDBHelper.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
