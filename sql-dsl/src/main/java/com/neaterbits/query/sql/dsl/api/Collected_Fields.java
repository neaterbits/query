package com.neaterbits.query.sql.dsl.api;


// For group-by, order-by
abstract class Collected_Fields {

	private final Collector_Fields<?> fields;
	private final int [] columns;

	Collected_Fields(Collector_Fields<?> fields) {

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


	final Collector_Fields<?> getFields() {
		return fields;
	}


	final int[] getColumns() {
		return columns;
	}
}
