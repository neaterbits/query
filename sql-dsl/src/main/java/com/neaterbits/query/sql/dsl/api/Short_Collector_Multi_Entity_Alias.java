package com.neaterbits.query.sql.dsl.api;


final class Short_Collector_Multi_Entity_Alias<MODEL, RESULT>
	extends Short_Collector_Multi_Entity_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>

	implements ISQLLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>

	{


	Short_Collector_Multi_Entity_Alias(BaseQuery select, CollectedQueryResult_Entity_Multi result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
		Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
	
		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}
}
