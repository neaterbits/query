package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

public interface IAdhocSelectSource<MODEL, RESULT, T> {

	IAdhocWhereOrJoin<MODEL, RESULT, T> from(Collection<T> collection);
	
}
