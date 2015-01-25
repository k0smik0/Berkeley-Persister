package net.iubris.berkeley_persister.core.entitymodel;


import net.iubris.berkeley_persister.core.entitymodel.proxies.ConcurrentHashMapPersisterProxy;
import net.iubris.berkeley_persister.core.entitymodel.proxies.ConcurrentSkipListSetPersisterProxy;
import net.iubris.berkeley_persister.core.entitymodel.proxies.CopyOnWriteArrayListProxy;
import net.iubris.berkeley_persister.core.entitymodel.proxies.UrlPersisterProxy;

import com.sleepycat.persist.model.AnnotationModel;
import com.sleepycat.persist.model.EntityModel;

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
