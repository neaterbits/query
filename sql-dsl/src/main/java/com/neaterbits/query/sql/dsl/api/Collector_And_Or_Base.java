package com.neaterbits.query.sql.dsl.api;

abstract class Collector_And_Or_Base<MODEL, RESULT, AFTER_GROUP_BY> 

	extends Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> {

	Collector_And_Or_Base(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}

	Collector_And_Or_Base(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, EConditionsClause conditionsClause, CollectedQueryResult result) {
		super(last, conditionsClause, result);
	}
	
	@Deprecated // TODO necessary?
	Collector_And_Or_Base(Collector_GroupBy<MODEL, RESULT> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}
	
	@Deprecated // TODO necessary?
	Collector_And_Or_Base(Collector_Query<MODEL> queryCollector, ICollectorClause collector) {
		super(queryCollector, collector);
	}
}
