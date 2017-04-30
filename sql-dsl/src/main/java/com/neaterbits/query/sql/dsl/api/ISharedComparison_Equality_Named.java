package com.neaterbits.query.sql.dsl.api;

@Deprecated // not necessary? no getters on rhs
public interface ISharedComparison_Equality_Named<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedComparison_Equality_All<MODEL, RESULT, R, L> {

	/*
	<T> L isEqualTo(Function<T, R> other);

	<T> L isNotEqualTo(Function<T, R> other);
	*/
	
}
