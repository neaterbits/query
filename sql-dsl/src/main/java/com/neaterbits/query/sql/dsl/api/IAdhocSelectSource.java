package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

public interface IAdhocSelectSource<RESULT> {

	<T> IAdhocWhereOrJoin<RESULT, T> from(Collection<T> collection);
	
	
}
