package com.neaterbits.query.sql.dsl.api;


final class CompiledConditionComparison extends CompiledCondition {

	private final CollectedCondition_NonNested original;
	//private final CompiledFieldReference lhs;
	private final CompiledExpression lhs;
	private final ConditionValue value;
	
	//private final ScalarType scalarType; // All conditions are scalars
	
	CompiledConditionComparison(CollectedCondition_NonNested original, CompiledExpression lhs, /* CompiledFieldReference lhs, */ ConditionValue value) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (lhs == null) {
			throw new IllegalArgumentException("lhs == null");
		}
		
		this.original = original;
		this.lhs = lhs;
		this.value = value;

		/*

		final Method getterMethod = lhs.getGetter().getGetterMethod();
		final Class<?> type = getterMethod.getReturnType();

		this.scalarType = ScalarType.fromType(type);

		if (scalarType == null) {
			throw new IllegalArgumentException("Unable to get scalar type from " + getterMethod);
		}
		*/
	}

	CollectedCondition getOriginal() {
		return original;
	}

	/*
	CompiledFieldReference getLhs() {
		return lhs;
	}

	TypeMapSource getLhsSource() {
		return lhs.getSource();
	}
	*/
	
	EClauseOperator getOperator() {
		return original.getOperator();
	}

	ConditionValue getValue() {
		return value;
	}

	CompiledExpression getLhs() {
		return lhs;
	}
/*
	int getNumFunctions() {
		final CollectedFunctions functions = original.getFunctions();
		
		return functions == null ? 0 : functions.getFunctions().size();
	}
	
	FunctionBase getFunctionAt(int functionIdx) {
		final CollectedFunctions functions = original.getFunctions();

		return functions.getFunctions().get(functionIdx).getFunction();
	}
	
	ScalarType getScalarType() {
		return scalarType;
	}

	@Override
	int getSourceIdx() {
		return lhs.getSource().getIdx();
	}
*/
	
	
	
	
}
