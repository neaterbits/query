package com.neaterbits.query.sql.dsl.api;

import java.util.List;

/**
 * Base class for wrapping running of-queries
 * eg. wrapping a JDBC PreparedQuery or a JPA QUery
 * 
 * @author nhl
 *
 */

abstract class QueryRunner {

	abstract void setParam(BaseParamImpl<?> param, int idx, Object value);

	abstract Object executeForSingleResult();
	
	abstract List<?> executeForMultiResult();

}
