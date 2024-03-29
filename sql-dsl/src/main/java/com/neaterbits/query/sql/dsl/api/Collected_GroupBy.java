package com.neaterbits.query.sql.dsl.api;

final class Collected_GroupBy extends Collected_Fields {

	private final ICollectorClause having;
	
	Collected_GroupBy(Collector_GroupBy<?, ?> collector) {
		super(collector);
		
		this.having = collector.getHaving();
	}

	Collected_GroupBy(int[] columns, ICollectorClause having) {
		super(columns);
		
		this.having = having;
	}

	ICollectorClause getHaving() {
		return having;
	}
}
