package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Single_Mapped_Any<
		MODEL, 
		RESULT,
		AFTER_GROUP_BY> 
	extends Short_Collector_Single_Any_Any<MODEL, RESULT, AFTER_GROUP_BY>
			
{
	Short_Collector_Single_Mapped_Any(BaseQuery select, CollectedQueryResult_Mapped_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}

	Short_Collector_Single_Mapped_Any(BaseQuery select, CollectedQueryResult_Entity_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}
}
