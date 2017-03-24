package com.neaterbits.query.sql.dsl.api;

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
	void resolve(QueryDialect_SQL dialect, QueryBuilder sb, ParamValueResolver resolver) {
		sb.append(getConditionResolvedPrefix());
	}

	@Override
	boolean isUnresolved() {
		return false;
	}
}
