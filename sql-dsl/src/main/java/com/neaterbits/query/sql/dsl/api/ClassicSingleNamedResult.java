package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

@Deprecated // should use regular named one
final class ClassicSingleNamedResult<MODEL, RESULT>
	extends Collector_Conditions_Initial<MODEL, RESULT, Void> 
		implements
			IClassicSingleWhereClauseBuilderNamed<MODEL, RESULT>,
			IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>
				  {
	
	ClassicSingleNamedResult(ClassicSelect select, CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(
				makeCollector(select, modelCompiler, result),
				EConditionsClause.WHERE);
	}
	
	private static <M> Collector_Query<M> makeCollector(ClassicSelect select, ModelCompiler<M> modelCompiler, CollectedQueryResult result) {
		final Collector_Query<M> collector = new QueryCollectorImpl<>(select, modelCompiler, result);
		
		collector.setSources(new CollectedSelectSource_Named(new Class<?> [] { result.getType() }));
		
		return collector;
	}
	
	// ------------------------  WHERE ------------------------


	@Override
	public <RR extends Comparable<RR>> ISharedComparison_Equality_All<MODEL, RESULT, RR, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>>
			where(Function<RESULT, RR> func) {

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, IClassicSingleAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetterExpression(func));
	}

	@Override
	public ISharedComparison_Comparable_String_All<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>>
			where(IFunctionString<RESULT> func) {

		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetterExpression(func));
	}

	// ------------------------  AND ------------------------
	@Override
	public ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>>
			and(IFunctionInteger<RESULT> getter) {
		
		final SQL_Collector_And_Named_Single<MODEL, RESULT> andClauses = new SQL_Collector_And_Named_Single<>(this);
		
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetterExpression(getter));
	}

	@Override
	public ISharedComparison_Comparable_String_All<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>>
			and(IFunctionString<RESULT> getter) {

		final SQL_Collector_And_Named_Single<MODEL, RESULT> andClauses = new SQL_Collector_And_Named_Single<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetterExpression(getter));
	}
	
	// ------------------------  OR ------------------------
	@Override
	public ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>>
			or(IFunctionInteger<RESULT> getter) {

		final Classic_Collector_Or_Named_Single<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named_Single<>(this);
		
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetterExpression(getter));
	}

	@Override
	public ISharedComparison_Comparable_String_All<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>>
			or(IFunctionString<RESULT> getter) {

		final Classic_Collector_Or_Named_Single<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named_Single<>(this);

		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetterExpression(getter));
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}
