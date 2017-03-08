package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

abstract class Collector_SharedFunctions_Base<MODEL, RESULT> {
	
	private final List<FunctionExpression> functions;


	/*
	Collector_SharedFunctions_Base() {
		this.functions = new ArrayList<>();
	}
	*/

	Collector_SharedFunctions_Base(Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		this.functions = 
				
			last != null 
				? new ArrayList<>(last.functions)
				: new ArrayList<>();
	}
	
	final void add(FunctionBase function) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		functions.add(new FunctionExpression(function));
	}
	
	final CollectedFunctions collect() {
		return new CollectedFunctions(functions);
	}
	
}

