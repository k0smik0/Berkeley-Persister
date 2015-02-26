/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (EntityModelFactory.java) is part of BerkeleyPersister.
 * 
 *     EntityModelFactory.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     EntityModelFactory.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.jbbp.core.entitymodel;


import net.iubris.jbbp.core.entitymodel.proxies.ConcurrentHashMapPersisterProxy;
import net.iubris.jbbp.core.entitymodel.proxies.ConcurrentSkipListSetPersisterProxy;
import net.iubris.jbbp.core.entitymodel.proxies.CopyOnWriteArrayListProxy;
import net.iubris.jbbp.core.entitymodel.proxies.UrlPersisterProxy;

import com.sleepycat.persist.model.AnnotationModel;
import com.sleepycat.persist.model.EntityModel;

/**
 * A base factory for a enhanced EntityModel, 
 * including support for multithread collections 
 * (ConcurrentSkipListSet, ConcurrentHashMap, CopyOnWriteArrayList)   
 */
public class EntityModelFactory {

	public static EntityModel getEnhancedEntityModel() {
		EntityModel entityModel = new AnnotationModel();
		entityModel.registerClass( ConcurrentSkipListSetPersisterProxy.class );
		entityModel.registerClass( ConcurrentHashMapPersisterProxy.class);
		entityModel.registerClass( CopyOnWriteArrayListProxy.class );
		entityModel.registerClass( UrlPersisterProxy.class );
		return entityModel;
	}
}
