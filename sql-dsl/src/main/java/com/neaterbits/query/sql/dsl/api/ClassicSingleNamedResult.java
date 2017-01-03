package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class ClassicSingleNamedResult<MODEL, RESULT>
	extends CollectedClauses_Initial<MODEL, RESULT> 
		implements
			IClassicSingleWhereClauseBuilderNamed<MODEL, RESULT>,
			IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>
				  {
	ClassicSingleNamedResult(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(
				makeCollector(result),
				modelCompiler,
				new Collector_Clause());
		
		getQueryCollector().setClauses(super.clauseCollector);
	}
	
	private static QueryCollectorImpl makeCollector(CollectedQueryResult result) {
		final QueryCollectorImpl collector = new QueryCollectorImpl(result);
		
		collector.setSources(new CollectedSelectSource_Named(new Class<?> [] { result.getType() }));
		
		return collector;
	}
	
	// ------------------------  WHERE ------------------------


	@Override
	public <RR> ISharedClauseConditionAll<MODEL, RESULT, RR, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>>
			where(Function<RESULT, RR> func) {

		return new CollectedClause_Condition<MODEL, RESULT, RR, IClassicSingleAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(func));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>>
			where(StringFunction<RESULT> func) {

		return new CollectedClause_String<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(func));
	}

	// ------------------------  AND ------------------------
	@Override
	public ISharedClauseConditionNamed<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>>
			and(IFunctionInteger<RESULT> getter) {
		
		final ClassicCollectedAndClauses_Named_Single<MODEL, RESULT> andClauses = new ClassicCollectedAndClauses_Named_Single<>(this);
		
		return new CollectedClause_Condition<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>>
			and(StringFunction<RESULT> getter) {

		final ClassicCollectedAndClauses_Named_Single<MODEL, RESULT> andClauses = new ClassicCollectedAndClauses_Named_Single<>(this);
		
		return new CollectedClause_String<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	// ------------------------  OR ------------------------
	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>>
			or(IFunctionInteger<RESULT> getter) {

		final ClassicCollectedOrClauses_Named_Single<MODEL, RESULT> orClauses = new ClassicCollectedOrClauses_Named_Single<>(this);
		
		return new CollectedClause_Condition<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>>
			or(StringFunction<RESULT> getter) {

		final ClassicCollectedOrClauses_Named_Single<MODEL, RESULT> orClauses = new ClassicCollectedOrClauses_Named_Single<>(this);

		return new CollectedClause_String<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
}
