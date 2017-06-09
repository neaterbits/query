package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_Or_Named_Single<MODEL, RESULT>

		extends Collector_Or<MODEL, RESULT, Void>

		implements IClassicSingleOrClausesNamed<MODEL, RESULT> {
	
	Classic_Collector_Or_Named_Single(ClassicSingleNamedResult<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<RESULT> getter) {
		//return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetterExpression(getter));
		return new Condition_ExpressionList_Comparable_Named<>(this, makeGetterExpression(getter));
	}

	@Override
	public ISharedComparison_Comparable_String_All<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(IFunctionString<RESULT> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetterExpression(getter));
	}
	@Override

	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}
