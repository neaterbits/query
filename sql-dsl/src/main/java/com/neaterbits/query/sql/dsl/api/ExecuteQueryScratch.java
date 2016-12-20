package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class ExecuteQueryScratch extends ConditionValuesScratch {
	private final Object [] scratch;
	private final int numResultParts;
	private final int numConditions;
	private final QueryMetaModel queryMetaModel;
	
	
	ExecuteQueryScratch(int numResultParts, int numSelectSources, int numConditions, QueryMetaModel queryMetaModel) {
		
		// TODO: Handle 1 result part case? no need for array
		this.scratch = new Object[numSelectSources];
		this.numResultParts = numResultParts;
		this.numConditions = numConditions;
		this.queryMetaModel = queryMetaModel;
	}
	
	Object get(int idx) {
		return scratch[idx];
	}
	
	void set(int idx, Object instance) {
		scratch[idx] = instance;
	}
	
	boolean numResultPartsIs(int num) {
		return numResultParts == num;
	}
	
	int getNumConditions() {
		return numConditions;
	}

	QueryMetaModel getQueryMetaModel() {
		return queryMetaModel;
	}
}
