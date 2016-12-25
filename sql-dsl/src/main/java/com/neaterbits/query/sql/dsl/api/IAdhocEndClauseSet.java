package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public interface IAdhocEndClauseSet<MODEL, T, RESULT_SET extends Set<T>>
	extends IAdhocEndClauseCollection<MODEL, T, RESULT_SET> {

		<L extends Set<T>> L as(Function<Collection<T>, L> function);
	
	
}
