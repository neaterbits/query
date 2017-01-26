package com.neaterbits.query.sql.dsl.api;

final class JPAConditionResolved extends JPACondition {

	private final Param<?> anyResolvedParam;
	
	JPAConditionResolved(String prefix, Param<?> anyResolvedParam) {
		super(prefix);
		
		// may be null
		this.anyResolvedParam = anyResolvedParam;
	}

	Param<?> getAnyResolvedParam() {
		return anyResolvedParam;
	}

	@Override
	void resolve(StringBuilder sb, ParamValueResolver resolver) {
		sb.append(getConditionResolvedPrefix());
	}

	@Override
	boolean isUnresolved() {
		return false;
	}
}
