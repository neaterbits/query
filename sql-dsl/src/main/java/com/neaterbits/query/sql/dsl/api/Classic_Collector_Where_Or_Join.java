package com.neaterbits.query.sql.dsl.api;


@Deprecated
abstract class Classic_Collector_Where_Or_Join<MODEL, RESULT, AFTER_GROUP_BY> 

	extends Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> 
			
	implements ISharedLogical_Where<MODEL, RESULT> {

	@Deprecated
	Classic_Collector_Where_Or_Join(Collector_Base<MODEL> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}

	@Deprecated
	Classic_Collector_Where_Or_Join(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		super(queryCollector, collector);
	}
}
