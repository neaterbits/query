package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class SingleTableResultImpl<MODEL, RESULT>
	extends ClausesImplInitial<MODEL, RESULT> 
		implements
			IClassicSingleWhereClauseBuilderTable<MODEL, RESULT>,
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
	public <RR> ISharedConditionClause<MODEL, RESULT, RR, AndOrLogicalClausesTableSingle<MODEL, RESULT>>
			where(Function<RESULT, RR> func) {

		return new ConditionClauseImpl<MODEL, RESULT, RR, AndOrLogicalClausesTableSingle<MODEL,RESULT>>(this, makeGetter(func));
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, AndOrLogicalClausesTableSingle<MODEL, RESULT>>
			where(StringFunction<RESULT> func) {

		return new StringClauseImpl<MODEL, RESULT, AndOrLogicalClausesTableSingle<MODEL,RESULT>>(this, makeGetter(func));
	}

	// ------------------------  AND ------------------------
	@Override
	public ISharedConditionClauseTable<MODEL, RESULT, Integer, IClassicSingleAndClausesTable<MODEL, RESULT>>
			and(IntegerFunction<RESULT> getter) {
		
		final AndClausesImplTableSingle<MODEL, RESULT> andClauses = new AndClausesImplTableSingle<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, Integer, IClassicSingleAndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, IClassicSingleAndClausesTable<MODEL, RESULT>>
			and(StringFunction<RESULT> getter) {

		final AndClausesImplTableSingle<MODEL, RESULT> andClauses = new AndClausesImplTableSingle<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, IClassicSingleAndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	// ------------------------  OR ------------------------
	@Override
	public ISharedConditionClause<MODEL, RESULT, Integer, IClassicSingleOrClausesTable<MODEL, RESULT>>
			or(IntegerFunction<RESULT> getter) {

		final OrClausesImplTableSingle<MODEL, RESULT> orClauses = new OrClausesImplTableSingle<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, Integer, IClassicSingleOrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, IClassicSingleOrClausesTable<MODEL, RESULT>>
			or(StringFunction<RESULT> getter) {

		final OrClausesImplTableSingle<MODEL, RESULT> orClauses = new OrClausesImplTableSingle<>(this);

		return new StringClauseImpl<MODEL, RESULT, IClassicSingleOrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
}
