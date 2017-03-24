package com.neaterbits.query.sql.dsl.api;

abstract class PreparedQueryComparisonRHS {

	abstract void resolve(QueryDialect_SQL dialect, QueryBuilder sb, ParamValueResolver paramValueResolver);
	
	abstract boolean isUnresolved();
	
}
