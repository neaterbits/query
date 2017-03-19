package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Single_Entity_Named<MODEL, RESULT>




		extends Short_Collector_Single_Entity_Any<
			MODEL,
			RESULT,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> 

{

	Short_Collector_Single_Entity_Named(Collector_Query<MODEL> queryCollector, Collector_Clause clauseCollector) {
		super(queryCollector, clauseCollector);
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(
			Collector_Base<MODEL> last, 
			int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
	
	
}
