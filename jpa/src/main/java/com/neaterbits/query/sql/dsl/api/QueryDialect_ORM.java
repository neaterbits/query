package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;

abstract class QueryDialect_ORM extends QueryDialect_SQL {

	QueryDialect_ORM(IEntityModelUtil entityModelUtil) {
		super(entityModelUtil);
	}
}
