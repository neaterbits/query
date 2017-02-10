package com.neaterbits.query.sql.dsl.api;

final class Collected_GroupBy extends Collected_Fields {

	Collected_GroupBy(Collector_GroupBy<?, ?> collector) {
		super(collector);
	}

	Collected_GroupBy(int[] columns) {
		super(columns);
	}
}
