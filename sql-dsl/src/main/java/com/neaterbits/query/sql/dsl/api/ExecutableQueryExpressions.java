package com.neaterbits.query.sql.dsl.api;

public interface ExecutableQueryExpressions {

	// where conditions
	// having-conditions
	// mapping
	
	
	EExpressionType getExpressionType(int level, int [] context);

	CompiledFieldReference getFieldReference(int level, int [] context);
	
	FunctionBase getFunction(int level, int [] context);
	
	int getSubCount(int level, int [] context);

	Comparable<?> getValue(int level, int [] context);

	Operator getListOperator(int level, int [] context, int idx);
	
	
	// function-parameters? may nest indefinitely so just part of indices
}
