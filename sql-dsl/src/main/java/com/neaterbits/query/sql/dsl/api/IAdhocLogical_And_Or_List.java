package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public interface IAdhocLogical_And_Or_List<MODEL, R, RESULT extends List<R>>
		extends IAdhocLogical_And_Or<MODEL, RESULT, R>,
				IAdhocEnd_List<MODEL, R, RESULT> {

}
