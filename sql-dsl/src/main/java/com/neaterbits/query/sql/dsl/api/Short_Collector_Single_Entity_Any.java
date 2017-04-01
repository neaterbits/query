package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Single_Entity_Any<
		MODEL, 
		RESULT,
		AFTER_GROUP_BY>
		
		
		
		extends Short_Collector_Single_Any_Any<
			MODEL,
			RESULT,
			
			AFTER_GROUP_BY> 
{

	Short_Collector_Single_Entity_Any(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, CollectedQueryResult_Entity_Single result) {
		super(last, result);
	}
}
