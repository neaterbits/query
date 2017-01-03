package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

abstract class AdhocQueryNamed_Collection<
		MODEL,
		RESULT extends Collection<?>>

		extends AdhocQueryNamed<MODEL, RESULT> 
	{

	AdhocQueryNamed_Collection(ECollectionType collectionType, Collection<?> coll) {
		super(collectionType, coll);
	}
}
