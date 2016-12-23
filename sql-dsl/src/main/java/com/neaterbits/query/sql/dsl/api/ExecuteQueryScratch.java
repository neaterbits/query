package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

class ExecuteQueryScratch extends ConditionValuesScratch {
	private Object [] scratch;
	private int numResultParts;
	private int numConditions;
	private final QueryMetaModel queryMetaModel;
	
	ExecuteQueryScratch() {
		this(0, 0, 0, null);
	}
	
	ExecuteQueryScratch(int numResultParts, int numSelectSources, int numConditions, QueryMetaModel queryMetaModel) {
		
		// TODO: Handle 1 result part case? no need for array
		this.queryMetaModel = queryMetaModel;
		
		initScratchArea(numResultParts, numSelectSources, numConditions);
	}
		
	protected void initScratchArea(int numResultParts, int numSelectSources, int numConditions) {
		this.scratch = new Object[numSelectSources];
		this.numResultParts = numResultParts;
		this.numConditions = numConditions;
	}
	
	final Object get(int idx) {
		return scratch[idx];
	}
	
	final void set(int idx, Object instance) {
		scratch[idx] = instance;
	}
	
	final boolean numResultPartsIs(int num) {
		return numResultParts == num;
	}
	
	final int getNumConditions() {
		return numConditions;
	}

	final QueryMetaModel getQueryMetaModel() {
		return queryMetaModel;
	}
}
