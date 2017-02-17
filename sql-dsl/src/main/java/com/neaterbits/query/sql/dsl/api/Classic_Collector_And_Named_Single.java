package com.neaterbits.query.sql.dsl.api;


final class Classic_Collector_And_Named_Single<
		MODEL, 
		RESULT>
		extends Collector_And<MODEL, RESULT, Void>
	implements IClassicSingleAndClausesNamed<MODEL, RESULT> {

	Classic_Collector_And_Named_Single(ClassicSingleNamedResult<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<RESULT> getter) {
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(StringFunction<RESULT> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}
