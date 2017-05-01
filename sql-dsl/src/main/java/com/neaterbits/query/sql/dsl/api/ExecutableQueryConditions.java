package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;


/**
 * Reused for both where-conditions and having-conditions
 *
 * @param <QUERY> quey
 */

interface ExecutableQueryConditions<QUERY> {

	/**
	 * Get max depth of sub conditions
	 * @param query query to get max depth for
	 * 
	 *  - returns -1 if has no conditions at all
	 *  - 0 should never occur
	 *  - returns 1 if only has conditions at the root level
	 *  - returns 2+ if eg. has a nested or inside where-and at the topmost level, etc 
	 *  
	 * @return max depth
	 */

	int getConditionsMaxDepth(QUERY query);
	
	
	// Generic nested-condition evaluation
	ConditionsType getConditionsType(QUERY query, int level, int [] conditionIndices);

	//int getConditionSourceIdx(QUERY query, int level, int [] conditionIndices);

	boolean evaluateCondition(QUERY query, int level, int [] conditionIndices, Object instance, ConditionValuesScratch scratch);
	
	boolean isSubCondition(QUERY query, int level, int [] conditionIndices);
	
	int getConditionsCount(QUERY query, int level, int [] conditionIndices);

	EClauseOperator getOperator(QUERY query, int level, int [] conditionIndices);
	
	// LHS and RHS for condition type
	ExecutableQueryExpressions getLHS(QUERY query, int level, int [] conditionIndices);
	ExecutableQueryExpressions getRHS(QUERY query, int level, int [] conditionIndices);

	// for compiled-queries only?
	//CompiledFieldReference getConditionLhs(QUERY query, int level, int [] conditionIndices);

	ConditionValue getConditionValue(QUERY query, int level, int [] conditionIndices);

	// Obsoleted by expressions
	//int getConditionNumFunctions(QUERY query, int level, int [] conditionIndices);

	// Obsoleted by expressions
	//FunctionCalcBase getConditionFunction(QUERY query, int level, int [] conditionIndices, int functionIdx);

	//Method getForDebugConditionLhsMethod(QUERY query, int level, int [] conditionIndices);
	
	String getForDebugConditionValue(QUERY query, int level, int [] conditionIndices);
	
	// Obsoleted by expressions
	/*
	default List<FunctionCalcBase> getConditionFunctions(QUERY query, int level, int [] conditionIndices) {
		final int numFunctions = getConditionNumFunctions(query, level, conditionIndices);
		
		final List<FunctionCalcBase> ret;
		
		if (numFunctions == 0) {
			ret = null;
		}
		else {
		
			ret = new ArrayList<>(numFunctions);
			
			for (int i = 0; i < numFunctions; ++ i) {
				
				final FunctionCalcBase function = getConditionFunction(query, level, conditionIndices, i);
				
				if (function == null) {
					throw new IllegalStateException("function == null");
				}
	
				ret.add(function);
			}
		}
		
		return ret;
		
	}
	*/
}
