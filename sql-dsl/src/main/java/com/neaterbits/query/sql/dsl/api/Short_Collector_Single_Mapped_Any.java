package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Single_Mapped_Any<
		MODEL, 
		RESULT,
		AFTER_GROUP_BY> 
	extends Short_Collector_Single_Any_Any<MODEL, RESULT, AFTER_GROUP_BY>
			
{
	Short_Collector_Single_Mapped_Any(Collector_Query<MODEL> queryCollector, CollectedQueryResult_Mapped_Single result) {
		super(queryCollector, result);
	}

	Short_Collector_Single_Mapped_Any(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}
}
