package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_MapToResult_Multi_Alias<MODEL, RESULT>
		extends Short_Collector_MapToResult_Multi<MODEL, RESULT> {

	Short_Collector_MapToResult_Multi_Alias(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		
		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}
}
