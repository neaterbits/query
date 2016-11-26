package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class WhereClauseBuilderImpl<MODEL, RESULT>
	extends ClausesImpl<MODEL, RESULT>
	implements WhereClauseBuilder<MODEL, RESULT>,
	AndOrLogicalClauses<MODEL, RESULT> {

	WhereClauseBuilderImpl(BaseQueryEntity<MODEL> last) {
		super(last, new ClauseCollectorImpl());
	}

	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, AndOrLogicalClauses<MODEL, RESULT>>
			where(Function<T, RR> getter) {

		return new ConditionClauseImpl<MODEL, RESULT, RR, AndOrLogicalClauses<MODEL,RESULT>>(this, getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndOrLogicalClauses<MODEL, RESULT>>
			where(StringFunction<T> getter) {
		
		return new StringClauseImpl<MODEL, RESULT, AndOrLogicalClauses<MODEL,RESULT>>(this, getter);
	}

	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, AndClauses<MODEL, RESULT>>
			and(Function<T, RR> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClauses<MODEL,RESULT>>(andClauses, getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndClauses<MODEL, RESULT>>
			and(StringFunction<T> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, AndClauses<MODEL,RESULT>>(andClauses, getter);
	}

	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, OrClauses<MODEL, RESULT>>
			or(Function<T, RR> getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClauses<MODEL,RESULT>>(orClauses, getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, OrClauses<MODEL, RESULT>> or(
			StringFunction<T> getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, OrClauses<MODEL,RESULT>>(orClauses, getter);
	}
}
