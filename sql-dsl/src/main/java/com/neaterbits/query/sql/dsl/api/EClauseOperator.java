package com.neaterbits.query.sql.dsl.api;

enum EClauseOperator {

	IS_EQUAL,
	NOT_EQUAL,
	
	GREATER_THAN,
	GREATER_OR_EQUAL,
	
	LESS_THAN,
	LESS_OR_EQUAL,
	
	IN,
	
	STARTS_WITH,
	ENDS_WITH,
	CONTAINS,
	MATCHES;
	
}
