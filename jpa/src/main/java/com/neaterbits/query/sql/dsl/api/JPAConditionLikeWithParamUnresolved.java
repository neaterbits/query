package com.neaterbits.query.sql.dsl.api;

final class JPAConditionLikeWithParamUnresolved extends JPAConditionUnresolved {

	private final boolean wildcardBefore;
	private final boolean wildcardAfter;
	
	public JPAConditionLikeWithParamUnresolved(String prefix, boolean wildcardBefore, boolean wildcardAfter) {
		super(prefix);

		this.wildcardBefore = wildcardBefore;
		this.wildcardAfter = wildcardAfter;
	}

	@Override
	void append(StringBuilder sb) {
		throw new UnsupportedOperationException("TODO");
	}
}
