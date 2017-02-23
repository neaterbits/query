package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Base<MODEL> extends CollectedItem {

	private final Collector_Query<MODEL> queryCollector;

	Collector_Base(Collector_Base<MODEL> last) {
		this(last.queryCollector);
	}

	Collector_Base(Collector_Query<MODEL> queryCollector) {
		
		if (queryCollector == null) {
			throw new IllegalArgumentException("queryCollector == null");
		}
		
		this.queryCollector = queryCollector;
	}

	final Collector_Query<MODEL> getQueryCollector() {
		return queryCollector;
	}
}
