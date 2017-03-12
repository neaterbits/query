package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;

final class ExecutableQueryConditions_ForCompiledQuery_Conditions implements ExecutableQueryConditions<CompiledQuery> {

	private static final ExecutableQueryForCompiledConditions forConditions = new ExecutableQueryForCompiledConditions();

	@Override
	public int getConditionsMaxDepth(CompiledQuery query) {
		return query.getConditionsMaxDepth();
	}

	@Override
	public ConditionsType getConditionsType(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionsType(query.getConditions(), level, conditionIndices);
	}

	@Override
	public int getConditionSourceIdx(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionSourceIdx(query.getConditions(), level, conditionIndices);
	}

	@Override
	public boolean evaluateCondition(CompiledQuery query, int level, int[] conditionIndices, Object instance, ConditionValuesScratch scratch) {
		return forConditions.evaluateCondition(query.getConditions(), level, conditionIndices, instance, scratch);
	}

	@Override
	public boolean isSubCondition(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.isSubCondition(query.getConditions(), level, conditionIndices);
	}

	@Override
	public int getConditionsCount(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionsCount(query.getConditions(), level, conditionIndices);
	}

	@Override
	public EClauseOperator getOperator(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getOperator(query.getConditions(), level, conditionIndices);
	}

	@Override
	public CompiledFieldReference getConditionLhs(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionLhs(query.getConditions(), level, conditionIndices);
	}

	@Override
	public ConditionValue getConditionValue(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionValue(query.getConditions(), level, conditionIndices);
	}

	@Override
	public int getConditionNumFunctions(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getConditionNumFunctions(query.getConditions(), level, conditionIndices);
	}

	@Override
	public FunctionCalcBase getConditionFunction(CompiledQuery query, int level, int[] conditionIndices, int functionIdx) {
		return forConditions.getConditionFunction(query.getConditions(), level, conditionIndices, functionIdx);
	}

	@Override
	public Method getForDebugConditionLhsMethod(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getForDebugConditionLhsMethod(query.getConditions(), level, conditionIndices);
	}

	@Override
	public String getForDebugConditionValue(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getForDebugConditionValue(query.getConditions(), level, conditionIndices);
	}

	@Override
	public ExecutableQueryExpressions getLHS(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getLHS(query.getConditions(), level, conditionIndices);
	}

	@Override
	public ExecutableQueryExpressions getRHS(CompiledQuery query, int level, int[] conditionIndices) {
		return forConditions.getRHS(query.getConditions(), level, conditionIndices);
	}
	
	
}
