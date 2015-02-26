/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (BerkeleyDBTransactionableHelper.java) is part of BerkeleyPersister.
 * 
 *     BerkeleyDBTransactionableHelper.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     BerkeleyDBTransactionableHelper.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.jbbp.core.helper;

import net.iubris.jbbp.core.entitymodel.EntityModelFactory;
import net.iubris.jbbp.core.helper.base.AbstractBerkeleyDBHelper;

import com.sleepycat.persist.model.EntityModel;

public class BerkeleyDBTransactionableHelper<K,V> extends AbstractBerkeleyDBHelper<K, V> {

	public BerkeleyDBTransactionableHelper(String corpusName, String siloName, EntityModel entityModel/*, BerkeleyDBHelperManager berkeleyDBHelperManager*/) {
		super(corpusName, siloName, true, true, entityModel/*, berkeleyDBHelperManager*/);
	}
	
	public BerkeleyDBTransactionableHelper(String corpusName, String siloName/*, BerkeleyDBHelperManager berkeleyDBHelperManager*/) {
		super(corpusName, siloName, true, true, EntityModelFactory.getEnhancedEntityModel()/*, berkeleyDBHelperManager*/);
	}

	@Override
	public boolean isTransactionable() {
		return true;
	}
}
