package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface IAdhocWhereOrJoin<RESULT, TYPE> {

	<V> IAdhocLogicalCondition<RESULT, V> where(Function<TYPE, V> getter);
	
}
