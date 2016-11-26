package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

public interface QueryOps<RESULT, RESULT_TYPE> extends Query<RESULT> {
	
	RESULT_TYPE execute(Collection<?> ... collections);
	
	RESULT_TYPE execute(QueryDataSource dataSource);

}
