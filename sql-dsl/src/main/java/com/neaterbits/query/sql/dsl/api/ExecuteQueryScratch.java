package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

class ExecuteQueryScratch extends ConditionValuesScratch {
	private Object [] scratch;
	private int numResultParts;
	private int numConditions;
	private int maxConditionsDepth;
	
	private int [] conditionIndices;

	private final QueryMetaModel queryMetaModel;
	
	ExecuteQueryScratch() {
		this(0, 0, 0, null, -1);
	}
	
	ExecuteQueryScratch(int numResultParts, int numSelectSources, int numConditions, QueryMetaModel queryMetaModel, int maxConditionsDepth) {
		
		// TODO: Handle 1 result part case? no need for array
		this.queryMetaModel = queryMetaModel;
		
		initScratchArea(numResultParts, numSelectSources, numConditions, maxConditionsDepth);
	}
		
	protected void initScratchArea(int numResultParts, int numSelectSources, int numConditions, int maxConditionsDepth) {
		this.scratch = new Object[numSelectSources];
		this.numResultParts = numResultParts;
		this.numConditions = numConditions;
		this.maxConditionsDepth = maxConditionsDepth;
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

	final boolean hasConditions() {
		return numConditions > 0;
	}

	final int [] assureConditionIndices() {
		if (conditionIndices == null) {
			conditionIndices = new int[maxConditionsDepth];
		}

		return conditionIndices;
	}

	final QueryMetaModel getQueryMetaModel() {
		return queryMetaModel;
	}
}
