package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class ClassicSingleNamedResult<MODEL, RESULT>
	extends Collector_Conditions_Initial<MODEL, RESULT, Void> 
		implements
			IClassicSingleWhereClauseBuilderNamed<MODEL, RESULT>,
			IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>
				  {
	ClassicSingleNamedResult(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(
				makeCollector(modelCompiler, result),
				new Collector_Clause(EConditionsClause.WHERE, ConditionsType.SINGLE));
	}
	
	private static <M> Collector_Query<M> makeCollector(ModelCompiler<M> modelCompiler, CollectedQueryResult result) {
		final Collector_Query<M> collector = new QueryCollectorImpl<>(modelCompiler, result);
		
		collector.setSources(new CollectedSelectSource_Named(new Class<?> [] { result.getType() }));
		
		return collector;
	}
	
	// ------------------------  WHERE ------------------------


	@Override
	public <RR extends Comparable<RR>> ISharedCondition_Equality_All<MODEL, RESULT, RR, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>>
			where(Function<RESULT, RR> func) {

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, IClassicSingleAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(func));
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>>
			where(StringFunction<RESULT> func) {

		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(func));
	}

	// ------------------------  AND ------------------------
	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>>
			and(IFunctionInteger<RESULT> getter) {
		
		final Classic_Collector_And_Named_Single<MODEL, RESULT> andClauses = new Classic_Collector_And_Named_Single<>(this);
		
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>>
			and(StringFunction<RESULT> getter) {

		final Classic_Collector_And_Named_Single<MODEL, RESULT> andClauses = new Classic_Collector_And_Named_Single<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	// ------------------------  OR ------------------------
	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>>
			or(IFunctionInteger<RESULT> getter) {

		final Classic_Collector_Or_Named_Single<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named_Single<>(this);
		
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>>
			or(StringFunction<RESULT> getter) {

		final Classic_Collector_Or_Named_Single<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named_Single<>(this);

		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}
