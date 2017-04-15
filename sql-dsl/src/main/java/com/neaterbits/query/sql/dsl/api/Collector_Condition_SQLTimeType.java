package com.neaterbits.query.sql.dsl.api;

final class Collector_Condition_SQLTimeType<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>> 
		extends Collector_Condition_Equality<MODEL, RESULT, R, L> 

		implements 
			ISharedCondition_SQLTimeType_All<MODEL, RESULT, R, L> {

	Collector_Condition_SQLTimeType(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {

		super(clause, expression);
	}
}
		