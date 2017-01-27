package com.neaterbits.query.sql.dsl.api;

public interface IAdhocLogical_Where_Or_Join_Singular<MODEL, RESULT, ENTITY> 
	extends IAdhoc_Where_Or_Join<MODEL, RESULT, ENTITY, IAdhocLogical_And_Or_Singular<MODEL, RESULT, ENTITY>, IAdhocLogical_Where_Or_Join_Singular<MODEL, RESULT, ENTITY>>,

			IAdhocEnd_Singular<MODEL, RESULT> {

	
}


