package com.neaterbits.query.sql.dsl.api;

abstract class PreparedQueryComparisonRHS {

	abstract void resolve(StringBuilder sb, ParamValueResolver paramValueResolver);
	
	abstract boolean isUnresolved();
	
}
