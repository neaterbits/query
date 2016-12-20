package com.neaterbits.query.sql.dsl.api;

final class ExecuteQueryScratch extends ConditionValuesScratch {
	private final Object [] scratch;
	
	ExecuteQueryScratch(int num) {
		this.scratch = new Object[num];
	}
	
	Object get(int idx) {
		return scratch[idx];
	}
	
	void set(int idx, Object instance) {
		scratch[idx] = instance;
	}
	
	int length() {
		return scratch.length;
	}
}
