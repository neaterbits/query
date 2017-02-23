package com.neaterbits.query.sql.dsl.api;

abstract class PreparedQueryComparisonRHS {

	abstract void resolve(QueryBuilder sb, ParamValueResolver paramValueResolver);
	
	abstract boolean isUnresolved();
	
}
