package com.neaterbits.query.sql.dsl.api;

public interface ExecutableQueryExpressions {

	// where conditions
	// having-conditions
	// mapping
	
	
	EExpressionType getExpressionType(int level, int [] context);

	FunctionBase getFunction(int level, int [] context);
	
	int getNumListMembers(int level, int [] context);

	Comparable<?> getValue(int level, int [] context);

	// function-parameters? may nest indefinitely so just part of indices
}
