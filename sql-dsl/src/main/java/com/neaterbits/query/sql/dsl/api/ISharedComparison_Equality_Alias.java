package com.neaterbits.query.sql.dsl.api;


@Deprecated // not necessary? no getters on rhs
public interface ISharedComparison_Equality_Alias<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedComparison_Equality_All<MODEL, RESULT, R, L>{

	/*
	L isEqualTo(Supplier<R> other);

	L isNotEqualTo(Supplier<R> other);
	*/
	
	
}
