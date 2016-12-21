package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparableStringValue<MODEL, RESULT, L extends ISharedLogicalClauses<MODEL, RESULT>> 
	extends ISharedClauseComparableStringBase<MODEL, RESULT, L> {

	L startsWith(String s);

    L endsWith(String s);

    L contains(String s);

    L matches(String regex);

}
