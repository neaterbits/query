package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ComparativeClauseAlias<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedClauseComparativeBaseAll<MODEL, RESULT, R, L>,
        ISharedConditionClauseAlias<MODEL, RESULT, R, L> {

	ISharedLogicalClauses<MODEL, RESULT> isGreaterThan(Supplier<R> getter);
	
	ISharedLogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Supplier<R> getter);
	
	ISharedLogicalClauses<MODEL, RESULT> isLesserThan(Supplier<R> getter);
	
	ISharedLogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Supplier<R> getter);

}
