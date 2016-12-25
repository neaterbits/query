package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

abstract class AdhocQueryClassCollection<MODEL, RESULT extends Collection<?>> extends AdhocQueryClass<MODEL, RESULT> {

	AdhocQueryClassCollection(ECollectionType collectionType, Collection<?> coll) {
		super(collectionType, coll);
	}

}
