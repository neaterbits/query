package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

abstract class Collector_SharedFunctions_Base<
		MODEL,
		RESULT,

		// commented out since reused for mapping functions 
		RET // extends ISharedLogical_Base<MODEL, RESULT>

		> {
	
	private final List<FunctionBase> functions;


	Collector_SharedFunctions_Base() {
		this.functions = new ArrayList<>();
	}

	final void add(FunctionBase function) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		functions.add(function);
	}
	
	final CollectedFunctions collect() {
		return new CollectedFunctions(functions);
	}
	
}

