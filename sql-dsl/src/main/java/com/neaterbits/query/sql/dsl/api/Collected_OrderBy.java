package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;

final class Collected_OrderBy extends Collected_Fields {

	private final ESortOrder [] sortOrders;
	
	Collected_OrderBy(Collector_OrderBy<?, ?> collector, ESortOrder [] sortOrders) {
		super(collector);
		
		this.sortOrders = sortOrders;
	}

	Collected_OrderBy(int[] columns) {
		super(columns);

		this.sortOrders = new ESortOrder[columns.length];
		
		Arrays.fill(sortOrders, Collector_OrderBy.DEFAULT_SORT_ORDER);
	}

	ESortOrder[] getSortOrders() {
		return sortOrders;
	}
}
