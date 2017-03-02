package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_MapToResult_Multi_Named<MODEL, RESULT>
		extends Short_Collector_MapToResult_Multi<MODEL, RESULT> {

	Short_Collector_MapToResult_Multi_Named(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}
