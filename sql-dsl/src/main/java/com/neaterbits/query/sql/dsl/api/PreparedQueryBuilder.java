package com.neaterbits.query.sql.dsl.api;


abstract class PreparedQueryBuilder {
	
	abstract void resolveFromParams(PreparedQueryConditionsBuilder conditionsBuilder, ParamValueResolver paramValueResolver);
	
	abstract String getQueryAsString();
}

