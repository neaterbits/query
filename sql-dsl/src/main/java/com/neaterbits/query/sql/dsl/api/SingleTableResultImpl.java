package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class SingleTableResultImpl<MODEL, RESULT>
	extends ClausesImplInitial<MODEL, RESULT> 
		implements
			WhereClauseBuilderTableSingle<MODEL, RESULT>,
			AndOrLogicalClausesTableSingle<MODEL, RESULT>
				  {
	SingleTableResultImpl(QueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(
				makeCollector(result),
				modelCompiler,
				new ClauseCollectorImpl());
		
		getQueryCollector().setClauses(super.clauseCollector);
	}
	
	private static QueryCollectorImpl makeCollector(QueryResult result) {
		final QueryCollectorImpl collector = new QueryCollectorImpl(result);
		
		collector.setSources(new SelectSourceClassesImpl(new Class<?> [] { result.getType() }));
		
		return collector;
	}
	
	// ------------------------  WHERE ------------------------


	@Override
	public <RR> ConditionClause<MODEL, RESULT, RR, AndOrLogicalClausesTableSingle<MODEL, RESULT>>
			where(Function<RESULT, RR> func) {

		return new ConditionClauseImpl<MODEL, RESULT, RR, AndOrLogicalClausesTableSingle<MODEL,RESULT>>(this, makeGetter(func));
	}

	@Override
	public StringClause<MODEL, RESULT, AndOrLogicalClausesTableSingle<MODEL, RESULT>>
			where(StringFunction<RESULT> func) {

		return new StringClauseImpl<MODEL, RESULT, AndOrLogicalClausesTableSingle<MODEL,RESULT>>(this, makeGetter(func));
	}

	// ------------------------  AND ------------------------
	@Override
	public <RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTableSingle<MODEL, RESULT>>
			and(Function<RESULT, RR> getter) {
		
		final AndClausesImplTableSingle<MODEL, RESULT> andClauses = new AndClausesImplTableSingle<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesTableSingle<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, AndClausesTableSingle<MODEL, RESULT>>
			and(StringFunction<RESULT> getter) {

		final AndClausesImplTableSingle<MODEL, RESULT> andClauses = new AndClausesImplTableSingle<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, AndClausesTableSingle<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	// ------------------------  OR ------------------------
	@Override
	public <RR> ConditionClause<MODEL, RESULT, RR, OrClausesTableSingle<MODEL, RESULT>>
			or(Function<RESULT, RR> getter) {

		final OrClausesImplTableSingle<MODEL, RESULT> orClauses = new OrClausesImplTableSingle<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesTableSingle<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, OrClausesTableSingle<MODEL, RESULT>>
			or(StringFunction<RESULT> getter) {

		final OrClausesImplTableSingle<MODEL, RESULT> orClauses = new OrClausesImplTableSingle<>(this);

		return new StringClauseImpl<MODEL, RESULT, OrClausesTableSingle<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
}
