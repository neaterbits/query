package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

public interface IAdhocLogical_And_Or_Set<MODEL, R, RESULT extends Set<R>>
		extends IAdhocLogical_And_Or<MODEL, RESULT, R>,
				IAdhocEnd_Set<MODEL, R, RESULT> {
	
}