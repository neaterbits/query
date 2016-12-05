package com.neaterbits.query.sql.dsl.api;

public interface ParamValueResolver {

	Object resolveParam(Param<?> param);

}
