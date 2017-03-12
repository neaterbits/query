package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_MultiResult_Decided_Alias<MODEL, RESULT>
		extends Short_Collector_MultiResult_Decided<MODEL, RESULT> {

	Short_Collector_MultiResult_Decided_Alias(BaseQuery select, CollectedQueryResult_Mapped_Multi result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);
	}
	
	Short_Collector_MultiResult_Decided_Alias(BaseQuery select, CollectedQueryResult_Entity_Multi result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		
		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}
}
