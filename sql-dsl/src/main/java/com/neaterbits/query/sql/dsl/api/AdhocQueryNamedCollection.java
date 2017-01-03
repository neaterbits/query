package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

abstract class AdhocQueryNamedCollection<
		MODEL,
		RESULT extends Collection<?>>

		extends AdhocQueryNamed<MODEL, RESULT> 
	{

	AdhocQueryNamedCollection(ECollectionType collectionType, Collection<?> coll) {
		super(collectionType, coll);
	}
}
