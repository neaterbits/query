package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Conditions_Base<MODEL, RESULT> extends Collector_Base<MODEL> {


	final Collector_Clause clauseCollector;

	EConditionsClause getConditionsClause() {
		return clauseCollector.getConditionsClause();
	}
	
	Collector_Conditions_Base(Collector_Conditions_Initial<MODEL, RESULT, ?> last, ConditionsType newConditionsType) {
		super(last);
		
		if (last.clauseCollector.getConditionsType() != ConditionsType.SINGLE) {
			throw new IllegalArgumentException("Only call this constructor after WHERE");
		}
		
		this.clauseCollector = new Collector_Clause(last.clauseCollector, newConditionsType);
	}

	Collector_Conditions_Base(Collector_Base<MODEL> last, Collector_Clause collector) {
		super(last);

		this.clauseCollector = collector;
	}
	
	Collector_Conditions_Base(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		super(queryCollector);
		
		this.clauseCollector = collector;
	}
}
