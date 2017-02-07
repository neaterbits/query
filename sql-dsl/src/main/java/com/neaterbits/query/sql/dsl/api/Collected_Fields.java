package com.neaterbits.query.sql.dsl.api;


// For group-by, order-by
final class Collected_Fields {

	private final Collector_Fields fields;
	private final int [] columns;

	Collected_Fields(Collector_Fields fields) {
		
		if (fields == null) {
			throw new IllegalArgumentException("fields == null");
		}

		this.fields = fields;
		this.columns = null;
	}
	
	
	Collected_Fields(int[] columns) {
		
		if (columns == null) {
			throw new IllegalArgumentException("columns == null");
		}

		this.fields = null;
		this.columns = columns;
	}


	Collector_Fields getFields() {
		return fields;
	}


	int[] getColumns() {
		return columns;
	}
}
