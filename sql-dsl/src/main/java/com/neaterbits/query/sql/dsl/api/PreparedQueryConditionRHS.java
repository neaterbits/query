package com.neaterbits.query.sql.dsl.api;

abstract class PreparedQueryConditionRHS {

	abstract void resolve(StringBuilder sb, ParamValueResolver paramValueResolver);
	
}
