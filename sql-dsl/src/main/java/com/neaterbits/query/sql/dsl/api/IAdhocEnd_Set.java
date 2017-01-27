package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public interface IAdhocEnd_Set<MODEL, T, RESULT_SET extends Set<T>>
	extends IAdhocEnd_Collection<MODEL, T, RESULT_SET> {

		<L extends Set<T>> L as(Function<Collection<T>, L> function);
	
	
}
