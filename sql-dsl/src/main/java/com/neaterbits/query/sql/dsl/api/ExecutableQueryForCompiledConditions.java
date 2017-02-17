package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;

final class ExecutableQueryForCompiledConditions
		extends ExecutableQueryForCompiledBase
		implements ExecutableQueryConditions<CompiledConditions> {

	static ConditionsType getConditionsType(CompiledConditions conditions) {
		final ConditionsType ret;
		
		if (conditions == null) {
			ret = ConditionsType.NONE;
		}
		else if (conditions instanceof CompiledConditions_And) {
			ret = ConditionsType.AND;
		}
		else if (conditions instanceof CompiledConditions_Or) {
			ret = ConditionsType.OR;
		}
		else if (conditions instanceof CompiledConditions_Single) {
			ret = ConditionsType.SINGLE;
		}
		else {
			throw new UnsupportedOperationException("Unknown condition type "
							+ conditions.getClass().getSimpleName());
		}

		return ret;
	}
	
	
	private CompiledCondition getCondition(CompiledConditions query, int level, int[] conditionIndices) {
		// First find CompiledConditions
		CompiledConditions conditions = getConditionsList(query, level, conditionIndices);
		

		return conditions.getConditions().get(conditionIndices[level]);
	}
	
	private CompiledConditionComparison getComparisonCondition(CompiledConditions query, int level, int[] conditionIndices) {

		final CompiledCondition condition = getCondition(query, level, conditionIndices);
		
		return (CompiledConditionComparison)condition;
	}

	/*
	private CompiledConditions getConditionsList(CompiledConditions query, int level, int[] conditionIndices) {
		return getConditionsList(query.getConditions(), level, conditionIndices);
	}
	*/

	private CompiledConditions getConditionsList(CompiledConditions cur, int level, int[] conditionIndices) {

		// Must loop over all indices
		for (int i = 0; i < level; ++ i) {
			final CompiledConditionNested nested = ((CompiledConditionNested)cur.getConditions().get(conditionIndices[i]));
			
			cur = nested.getSub();
		}

		return cur;
	}
	
	@Override
	public int getConditionsMaxDepth(CompiledConditions conditions) {
		return conditions.getMaxDepth();
	}

	@Override
	public ConditionsType getConditionsType(CompiledConditions conditions, int level, int[] conditionIndices) {
		
		final CompiledConditions c = getConditionsList(conditions, level, conditionIndices);

		return getConditionsType(c);
	}

	@Override
	public int getConditionSourceIdx(CompiledConditions conditions, int level, int[] conditionIndices) {
		return getCondition(conditions, level, conditionIndices).getSourceIdx();
	}

	@Override
	public boolean isSubCondition(CompiledConditions conditions, int level, int[] conditionIndices) {

		final CompiledCondition condition =  getCondition(conditions, level, conditionIndices);
			
		return condition instanceof CompiledConditionNested;
	}

	@Override
	public int getConditionsCount(CompiledConditions conditions, int level, int[] conditionIndices) {
		return getConditionsList(conditions, level, conditionIndices).getConditions().size();
	}

	@Override
	public EClauseOperator getOperator(CompiledConditions conditions, int level, int[] conditionIndices) {
		return getComparisonCondition(conditions, level, conditionIndices).getOperator();
	}

	@Override
	public CompiledFieldReference getConditionLhs(CompiledConditions conditions, int level, int[] conditionIndices) {
		return getComparisonCondition(conditions, level, conditionIndices).getLhs();
	}

	@Override
	public ConditionValue getConditionValue(CompiledConditions conditions, int level, int[] conditionIndices) {
		return getComparisonCondition(conditions, level, conditionIndices).getValue();
	}

	@Override
	public int getConditionNumFunctions(CompiledConditions conditions, int level, int[] conditionIndices) {
		return getComparisonCondition(conditions, level, conditionIndices).getNumFunctions();
	}


	@Override
	public FunctionBase getConditionFunction(CompiledConditions conditions, int level, int[] conditionIndices, int functionIdx) {
		return getComparisonCondition(conditions, level, conditionIndices).getFunctionAt(functionIdx);
	}
	
	@Override
	public boolean evaluateCondition(CompiledConditions conditions, int level, int[] conditionIndices, Object instance, ConditionValuesScratch scratch) {
		
		final CompiledConditionComparison comparison = getComparisonCondition(conditions, level, conditionIndices);
		
		return evaluateCondition(comparison, instance, scratch);
	}
	
	@Override
	public Method getForDebugConditionLhsMethod(CompiledConditions conditions, int level, int[] conditionIndices) {
		return getComparisonCondition(conditions, level, conditionIndices).getLhs().getGetter().getGetterMethod();
	}

	@Override
	public String getForDebugConditionValue(CompiledConditions conditions, int level, int[] conditionIndices) {
		return getComparisonCondition(conditions, level, conditionIndices).getValue().toString();
	}
	
	
}
