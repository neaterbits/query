package com.neaterbits.query.sql.dsl.api;

final class JPAConditionLikeWithParamUnresolved extends JPAConditionUnresolved {

	private final boolean wildcardBefore;
	private final boolean wildcardAfter;
	private final Param<?> param;
	
	public JPAConditionLikeWithParamUnresolved(String prefix, boolean wildcardBefore, boolean wildcardAfter, Param<?> param) {
		super(prefix);

		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}

		this.wildcardBefore = wildcardBefore;
		this.wildcardAfter = wildcardAfter;
		this.param = param;
	}

	@Override
	void resolve(StringBuilder sb, ParamValueResolver resolver) {

		if (resolver == null) {
			throw new IllegalArgumentException("resolver == null");
		}

		final Object value = resolver.resolveParam(param);
		
		if (value == null) {
			throw new IllegalStateException("No value for param " + param);
		}

		if (wildcardBefore) {
			sb.append("%");
		}

		sb.append((String)value);

		if (wildcardAfter) {
			sb.append("%");
		}
	}
}
