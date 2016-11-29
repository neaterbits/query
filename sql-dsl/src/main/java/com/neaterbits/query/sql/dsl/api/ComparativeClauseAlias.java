package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ComparativeClauseAlias<MODEL, RESULT, R extends Comparable<R>, L extends LogicalClauses<MODEL, RESULT>>
	extends ComparativeClause<MODEL, RESULT, R, L>,
        ConditionClauseAlias<MODEL, RESULT, R, L> {

	LogicalClauses<MODEL, RESULT> isGreaterThan(Supplier<R> getter);
	
	LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Supplier<R> getter);
	
	LogicalClauses<MODEL, RESULT> isLesserThan(Supplier<R> getter);
	
	LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Supplier<R> getter);

}
