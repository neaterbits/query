package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.function.Consumer;

public interface IAdhocJoin<MODEL, RESULT, JOIN_FROM, RET_TYPE extends IAdhocJoin<MODEL, RESULT, JOIN_FROM, RET_TYPE>> {

	// Join is formed similar to a sub-select, since we can only type-safely on one type at a time
	// while not utilizing reflection
	
	<JOIN_TO> RET_TYPE innerJoin(Collection<JOIN_TO> joinTo, Consumer<IAdhocJoinSub<MODEL, RESULT, JOIN_FROM, JOIN_TO>> consumer); 

	<JOIN_TO> RET_TYPE leftJoin(Collection<JOIN_TO> joinTo, Consumer<IAdhocJoinSub<MODEL, RESULT, JOIN_FROM, JOIN_TO>> consumer); 

}
