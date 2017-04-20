package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;

final class SQLConditionResolved extends SQLCondition {

	private final Param<?> anyResolvedParam;
	
	SQLConditionResolved(String prefix, Param<?> anyResolvedParam) {
		super(prefix);
		
		// may be null
		this.anyResolvedParam = anyResolvedParam;
	}

	Param<?> getAnyResolvedParam() {
		return anyResolvedParam;
	}

	@Override
	void resolve(CompiledFieldReference field, IEntityModelUtil entityModelUtil, QueryDialect_SQL dialect, QueryBuilder sb, ParamValueResolver resolver) {
		sb.append(getConditionResolvedPrefix());
	}

	@Override
	boolean isUnresolved() {
		return false;
	}
}
