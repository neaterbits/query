package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class OrClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT> implements OrClauses<MODEL, RESULT>{

	OrClausesImpl(WhereClauseBuilderImpl<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, OrClauses<MODEL, RESULT>> or(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClauses<MODEL,RESULT>>(this, getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, OrClauses<MODEL, RESULT>> or(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, OrClauses<MODEL,RESULT>>(this, getter);
	}
}
