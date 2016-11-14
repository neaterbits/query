package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class AndClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT> implements AndClauses<MODEL, RESULT> {

	AndClausesImpl(AndClausesImpl<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, AndClauses<MODEL, RESULT>> and(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClauses<MODEL,RESULT>>(this, getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndClauses<MODEL, RESULT>> and(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, AndClauses<MODEL,RESULT>>(this, getter);
	}
}
