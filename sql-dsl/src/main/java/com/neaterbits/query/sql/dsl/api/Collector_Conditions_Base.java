package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Conditions_Base<MODEL, RESULT> extends Collector_Base<MODEL> {


	final ICollectorClause clauseCollector;

	final EConditionsClause getConditionsClause() {
		return clauseCollector.getConditionsClause();
	}
	
	Collector_Conditions_Base(Collector_Conditions_Initial<MODEL, RESULT, ?> last, ConditionsType newConditionsType) {
		
		super(last);
		
		if (last.clauseCollector.getConditionsType() != ConditionsType.SINGLE) {
			throw new IllegalArgumentException("Only call this constructor after WHERE");
		}
		
		this.clauseCollector = last.clauseCollector.addConditionsType(newConditionsType);	
	}

	Collector_Conditions_Base(Collector_Conditions_Initial<MODEL, RESULT, ?> last, ConditionsType newConditionsType, CollectedQueryResult result) {
		super(new QueryCollectorImpl<>(last.getQueryCollector(), result));
		
		if (last.clauseCollector.getConditionsType() != ConditionsType.SINGLE) {
			throw new IllegalArgumentException("Only call this constructor after WHERE");
		}
		
		this.clauseCollector = last.clauseCollector.addConditionsType(newConditionsType);
	}

	// for nested-queries?
	
	@Deprecated // TODO can be removed?
	Collector_Conditions_Base(Collector_Base<MODEL> last, ICollectorClause collector) {
		super(last);

		this.clauseCollector = collector;
	}
	
	@Deprecated // TODO can be removed?
	Collector_Conditions_Base(Collector_Base<MODEL> last, ICollectorClause collector, CollectedQueryResult result) {
		super(new QueryCollectorImpl<>(last.getQueryCollector(), result));

		this.clauseCollector = collector;
	}

	@Deprecated // TODO can be removed?
	Collector_Conditions_Base(Collector_Query<MODEL> queryCollector, ICollectorClause collector) {
		super(queryCollector);
		
		this.clauseCollector = collector;
	}
	
	Collector_Conditions_Base(Collector_Query<MODEL> queryCollector, ICollectorClause collector, ConditionsType conditionsType) {
		super(queryCollector);

		this.clauseCollector = collector.addConditionsType(conditionsType);
	}
	
}
