/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (BerkeleyDBAutoCommittableHelper.java) is part of BerkeleyPersister.
 * 
 *     BerkeleyDBAutoCommittableHelper.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     BerkeleyDBAutoCommittableHelper.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.berkeley_persister.core.helper;

import net.iubris.berkeley_persister.core.helper.base.AbstractBerkeleyDBHelper;
import net.iubris.berkeley_persister.core.helper.base.utils.BerkeleyDBHelperManager;

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
