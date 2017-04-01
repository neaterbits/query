package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Base<MODEL> extends CollectedItem {

	private static final Boolean DEBUG = true;
	
	private final Collector_Query<MODEL> queryCollector;

	Collector_Base(Collector_Base<MODEL> last) {
		this(last.queryCollector, 0);

		if (DEBUG) {
			System.out.println("!! created collector " + getClass().getSimpleName() + " from " + getClass().getSimpleName() + " with collector " + last.getQueryCollector());
		}
	}
	
	Collector_Base(Collector_Query<MODEL> queryCollector) {
		this(queryCollector, 0);

		if (DEBUG) {
			System.out.println("!! created collector " + getClass().getSimpleName() + " with collector " + queryCollector);
		}
	}

	private Collector_Base(Collector_Query<MODEL> queryCollector, int foo) {


		if (queryCollector == null) {
			throw new IllegalArgumentException("queryCollector == null");
		}
		
		this.queryCollector = queryCollector;
	}

	final Collector_Query<MODEL> getQueryCollector() {
		return queryCollector;
	}
}
