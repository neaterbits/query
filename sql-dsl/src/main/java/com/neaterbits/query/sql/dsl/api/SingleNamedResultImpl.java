package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class SingleNamedResultImpl<MODEL, RESULT>
	extends ClausesImplInitial<MODEL, RESULT> 
		implements
			IClassicSingleWhereClauseBuilderNamed<MODEL, RESULT>,
			IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>
				  {
	SingleNamedResultImpl(QueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(
				makeCollector(result),
				modelCompiler,
				new ClauseCollectorImpl());
		
		getQueryCollector().setClauses(super.clauseCollector);
	}
	
	private static QueryCollectorImpl makeCollector(QueryResult result) {
		final QueryCollectorImpl collector = new QueryCollectorImpl(result);
		
		collector.setSources(new SelectSourceNamedImpl(new Class<?> [] { result.getType() }));
		
		return collector;
	}
	
	// ------------------------  WHERE ------------------------


	@Override
	public <RR> ISharedClauseConditionAll<MODEL, RESULT, RR, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>>
			where(Function<RESULT, RR> func) {

		return new ConditionClauseImpl<MODEL, RESULT, RR, IClassicSingleAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(func));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>>
			where(StringFunction<RESULT> func) {

		return new StringClauseImpl<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(func));
	}

	// ------------------------  AND ------------------------
	@Override
	public ISharedClauseConditionNamed<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>>
			and(IFunctionInteger<RESULT> getter) {
		
		final AndClausesImplNamedSingle<MODEL, RESULT> andClauses = new AndClausesImplNamedSingle<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>>
			and(StringFunction<RESULT> getter) {

		final AndClausesImplNamedSingle<MODEL, RESULT> andClauses = new AndClausesImplNamedSingle<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	// ------------------------  OR ------------------------
	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>>
			or(IFunctionInteger<RESULT> getter) {

		final OrClausesImplNamedSingle<MODEL, RESULT> orClauses = new OrClausesImplNamedSingle<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>>
			or(StringFunction<RESULT> getter) {

		final OrClausesImplNamedSingle<MODEL, RESULT> orClauses = new OrClausesImplNamedSingle<>(this);

		return new StringClauseImpl<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
}
