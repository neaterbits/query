package com.neaterbits.query.sql.dsl.api;

final class Collected_GroupBy extends Collected_Fields {

	private final Collector_Clause having;
	
	Collected_GroupBy(Collector_GroupBy<?, ?> collector) {
		super(collector);
		
		this.having = collector.getHaving();
	}

	Collected_GroupBy(int[] columns, Collector_Clause having) {
		super(columns);
		
		this.having = having;
	}

	Collector_Clause getHaving() {
		return having;
	}
}
