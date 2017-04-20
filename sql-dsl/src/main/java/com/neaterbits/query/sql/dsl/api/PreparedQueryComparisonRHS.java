package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;

abstract class PreparedQueryComparisonRHS {

	abstract void resolve(CompiledFieldReference field, IEntityModelUtil entityModelUtil, QueryDialect_SQL dialect, QueryBuilder sb, ParamValueResolver paramValueResolver);
	
	abstract boolean isUnresolved();
	
}
