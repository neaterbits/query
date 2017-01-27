package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

abstract class AdhocQuery_Named_Collection<
		MODEL,
		RESULT extends Collection<?>>

		extends AdhocQuery_Named<MODEL, RESULT> 
	{

	AdhocQuery_Named_Collection(ECollectionType collectionType, Collection<?> coll) {
		super(collectionType, coll);
	}
}
