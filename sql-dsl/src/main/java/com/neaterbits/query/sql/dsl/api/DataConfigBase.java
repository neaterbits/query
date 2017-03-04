package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

import com.neaterbits.query.sql.dsl.api.entity.EntityModel;

public abstract class DataConfigBase<ENTITIES ,MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, ATTRIBUT_COLL extends Collection<ATTRIBUTE>>

		extends DataConfig {

	protected abstract EntityModel<
			MANAGED,
			EMBEDDED,
			IDENTIFIABLE, 
			ATTRIBUTE,
			ATTRIBUT_COLL>
	
		getEntityModel();
	
	protected abstract DataStore<ENTITIES> getDataStore();
	
	protected abstract QueryDataSource getDataSource();
	
}
