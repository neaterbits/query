package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class WhereClauseBuilderImpl<MODEL, RESULT>
	extends ClausesImpl<MODEL, RESULT>
	implements WhereClauseBuilderTable<MODEL, RESULT>,
			   WhereClauseBuilderAlias<MODEL, RESULT>,
			   AndOrLogicalClausesTable<MODEL, RESULT>,
			   AndOrLogicalClausesAlias<MODEL, RESULT> {

	WhereClauseBuilderImpl(BaseQueryEntity<MODEL> last) {
		super(last, new ClauseCollectorImpl());
	}
	
	// ------------------------  WHERE ------------------------
	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, AndOrLogicalClausesTable<MODEL, RESULT>> where(Function<T, RR> getter) {

		return new ConditionClauseImpl<MODEL, RESULT, RR, AndOrLogicalClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndOrLogicalClausesTable<MODEL, RESULT>> where(StringFunction<T> getter) {
		
		return new StringClauseImpl<MODEL, RESULT, AndOrLogicalClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	@Override
	public <R> ConditionClause<MODEL, RESULT, R, AndOrLogicalClausesAlias<MODEL, RESULT>> where(Supplier<R> func) {

		return new ConditionClauseImpl<MODEL, RESULT, R, AndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(func));
	}

	@Override
	public StringClause<MODEL, RESULT, AndOrLogicalClausesAlias<MODEL, RESULT>> where(StringSupplier supplier) {

		return new StringClauseImpl<MODEL, RESULT, AndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(supplier));
	}

	// ------------------------  AND ------------------------
	@Override
	public <T, RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTable<MODEL, RESULT>> and(Function<T, RR> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, AndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	@Override
	public <RR> ConditionClauseAlias<MODEL, RESULT, RR, AndClausesAlias<MODEL, RESULT>> and(Supplier<RR> getter) {

		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, AndClausesAlias<MODEL, RESULT>> and(StringSupplier getter) {

		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, AndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	

	// ------------------------  OR ------------------------
	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, OrClausesTable<MODEL, RESULT>>
			or(Function<T, RR> getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	public <T> StringClause<MODEL, RESULT, OrClausesTable<MODEL, RESULT>> or(
			StringFunction<T> getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, OrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	
	@Override
	public <RR> ConditionClause<MODEL, RESULT, RR, OrClausesAlias<MODEL, RESULT>> or(Supplier<RR> getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, OrClausesAlias<MODEL, RESULT>> or(StringSupplier getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, OrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
	
	
}
