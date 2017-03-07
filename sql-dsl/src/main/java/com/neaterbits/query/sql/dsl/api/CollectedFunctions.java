package com.neaterbits.query.sql.dsl.api;

import java.util.Collections;
import java.util.List;

final class CollectedFunctions {
	private final List<FunctionExpression> functions;

	CollectedFunctions(List<FunctionExpression> functions) {
		
		if (functions == null) {
			throw new IllegalArgumentException("functions == null");
		}
		
		if (functions.isEmpty()) {
			throw new IllegalArgumentException("no functions");
		}
		
		this.functions = Collections.unmodifiableList(functions);
	}

	List<FunctionExpression> getFunctions() {
		return functions;
	}
}
