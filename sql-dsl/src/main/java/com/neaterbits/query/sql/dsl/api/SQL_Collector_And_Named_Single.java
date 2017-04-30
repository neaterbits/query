package com.neaterbits.query.sql.dsl.api;


final class SQL_Collector_And_Named_Single<
		MODEL, 
		RESULT>
		extends Collector_And<MODEL, RESULT, Void>
	implements IClassicSingleAndClausesNamed<MODEL, RESULT> {

	SQL_Collector_And_Named_Single(ClassicSingleNamedResult<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<RESULT> getter) {
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetterExpression(getter));
	}

	@Override
	public ISharedComparison_Comparable_String_All<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(IFunctionString<RESULT> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetterExpression(getter));
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}
