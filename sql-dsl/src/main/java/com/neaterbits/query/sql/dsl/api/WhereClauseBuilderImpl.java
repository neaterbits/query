package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class WhereClauseBuilderImpl<MODEL, RESULT>
	extends ClausesImplInitial<MODEL, RESULT>
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
	public <T> ConditionClauseTable<MODEL, RESULT, Integer, AndClausesTable<MODEL, RESULT>> and(IntegerFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ConditionClauseTable<MODEL, RESULT, Long, AndClausesTable<MODEL, RESULT>> and(LongFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, AndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	@Override
	public ConditionClauseAlias<MODEL, RESULT, Integer, AndClausesAlias<MODEL, RESULT>> and(IntegerSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ConditionClauseAlias<MODEL, RESULT, Long, AndClausesAlias<MODEL, RESULT>> and(LongSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public StringClause<MODEL, RESULT, AndClausesAlias<MODEL, RESULT>> and(StringSupplier getter) {

		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, AndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	

	// ------------------------  OR ------------------------

	@Override
	public <T> ConditionClause<MODEL, RESULT, Integer, OrClausesTable<MODEL, RESULT>> or(IntegerFunction<T> getter) {
		return orClassImpl(getter);
	}

	
	@Override
	public <T> ConditionClause<MODEL, RESULT, Long, OrClausesTable<MODEL, RESULT>> or(LongFunction<T> getter) {
		return orClassImpl(getter);
	}


	@Override
	public <T> StringClause<MODEL, RESULT, OrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter) {
		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, OrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
	
	@Override
	public ConditionClause<MODEL, RESULT, Integer, OrClausesAlias<MODEL, RESULT>> or(IntegerSupplier getter) {
		return orAliasImpl(this, getter);
	}
	
	@Override
	public ConditionClause<MODEL, RESULT, Long, OrClausesAlias<MODEL, RESULT>> or(LongSupplier getter) {
		return orAliasImpl(this, getter);
	}

	@Override
	public StringClause<MODEL, RESULT, OrClausesAlias<MODEL, RESULT>> or(StringSupplier getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, OrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	// ------------------------  AND helpers ------------------------

	private <T, RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTable<MODEL, RESULT>> andClassImpl(Function<T, RR> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	private <RR> ConditionClauseAlias<MODEL, RESULT, RR, AndClausesAlias<MODEL, RESULT>> andAliasImpl(Supplier<RR> getter) {

		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	// ------------------------  OR helpers ------------------------
	final <T, RR> ConditionClause<MODEL, RESULT, RR, OrClausesTable<MODEL, RESULT>> orClassImpl(Function<T, RR> getter) {
		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	final <RR> ConditionClause<MODEL, RESULT, RR, OrClausesAlias<MODEL, RESULT>> orAliasImpl(ClausesImplInitial<MODEL, RESULT> last, Supplier<RR> getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(last);

		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	
}
