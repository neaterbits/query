package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Conditions_Intermediate<MODEL, RESULT, AFTER_GROUP_BY>
		extends Collector_Conditions_GroupBy<MODEL, RESULT, AFTER_GROUP_BY>{

	Collector_Conditions_Intermediate(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, ConditionsType newConditionsType) {
		super(last, newConditionsType);
	}

	@Deprecated // TODO can be removed?
	Collector_Conditions_Intermediate(Collector_Base<MODEL> last, ICollectorClause collector) {
		super(last, collector);
	}
	
	@Deprecated // TODO can be removed?
	Collector_Conditions_Intermediate(Collector_Query<MODEL> queryCollector, ICollectorClause collector) {
		super(queryCollector, collector);
	}

	@Deprecated // TODO can be  switched for passing higher-level type?
	Collector_Conditions_Intermediate(Collector_Query<MODEL> queryCollector, ICollectorClause collector, ConditionsType conditionsType) {
		super(queryCollector, collector, conditionsType);
	}

}
