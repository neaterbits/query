package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;

final class ExecutableQueryConditions_ForCompiledQuery_Having implements ExecutableQueryConditions<CompiledQuery> {

	private static final ExecutableQueryForCompiledConditions forConditions = new ExecutableQueryForCompiledConditions();

	@Override
	public int getConditionsMaxDepth(CompiledQuery query) {
		return forConditions.getConditionsMaxDepth(query.getHaving());
	}

	@Override
	public ConditionsType getConditionsType(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionsType(query.getHaving(), level, conditionIndices);
	}

	@Override
	public int getConditionSourceIdx(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionSourceIdx(query.getHaving(), level, conditionIndices);
	}

	@Override
	public boolean evaluateCondition(CompiledQuery query, int level, int[] conditionIndices, Object instance, ConditionValuesScratch scratch) {
		return forConditions.evaluateCondition(query.getHaving(), level, conditionIndices, instance, scratch);
	}

	@Override
	public boolean isSubCondition(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.isSubCondition(query.getHaving(), level, conditionIndices);
	}

	@Override
	public int getConditionsCount(CompiledQuery query, int level, int[] conditionIndices) {
		return query.getResultProcessing() == null
					? 0
			        : query.getResultProcessing().getHaving() == null
			        	? 0
			            : forConditions.getConditionsCount(query.getHaving(), level, conditionIndices);
	}

	@Override
	public EClauseOperator getOperator(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getOperator(query.getHaving(), level, conditionIndices);
	}

	@Override
	public CompiledFieldReference getConditionLhs(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionLhs(query.getHaving(), level, conditionIndices);
	}

	@Override
	public ConditionValue getConditionValue(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionValue(query.getHaving(), level, conditionIndices);
	}

	@Override
	public int getConditionNumFunctions(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionNumFunctions(query.getHaving(), level, conditionIndices);
	}

	@Override
	public FunctionBase getConditionFunction(CompiledQuery query, int level, int[] conditionIndices, int functionIdx) {
		return forConditions.getConditionFunction(query.getHaving(), level, conditionIndices, functionIdx);
	}

	@Override
	public Method getForDebugConditionLhsMethod(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getForDebugConditionLhsMethod(query.getHaving(), level, conditionIndices);
	}

	@Override
	public String getForDebugConditionValue(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getForDebugConditionValue(query.getHaving(), level, conditionIndices);
	}
}
