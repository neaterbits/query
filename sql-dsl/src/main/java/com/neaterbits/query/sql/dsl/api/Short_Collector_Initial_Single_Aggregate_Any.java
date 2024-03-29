package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Initial_Single_Aggregate_Any<MODEL, RESULT>
	extends Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT> {
	
	Short_Collector_Initial_Single_Aggregate_Any(BaseQuery query, ModelCompiler<MODEL> modelCompiler, CollectedQueryResult_Aggregate result) {
		super(query, modelCompiler, result, null);
	}


	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {

		throw new UnsupportedOperationException("Not supported for aggregate queries");
	}
	
	@Override
	final Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereNamed() {
		return this;
	}

	@Override
	final Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereAlias() {
		return this;
	}
}