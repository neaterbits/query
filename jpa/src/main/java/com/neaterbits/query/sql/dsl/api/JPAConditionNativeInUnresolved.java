package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class JPAConditionNativeInUnresolved extends SQLConditionUnresolved {

	private final BaseParamImpl<?> param;
	
	JPAConditionNativeInUnresolved(String conditionResolvedPrefix, BaseParamImpl<?> param) {
		super(conditionResolvedPrefix);
		
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		this.param = param;
	}

	@Override
	void resolve(QueryDialect_SQL dialect, QueryBuilder sb, ParamValueResolver paramValueResolver) {
		
		final Object value = paramValueResolver.resolveParam(param);
		
		if (value == null) {
			throw new IllegalStateException("No value for param " + param);
		}
		
		sb.append(' ').append(getConditionResolvedPrefix());

		final List<?> list = (List<?>)value;
		
		sb.append('(');

		final int num = list.size();
		
		for (int i = 0; i < num; ++ i) {
			if (i > 0) {
				sb.append(',');
			}

			SQLConditionToOperator.appendLiteral(dialect, list.get(i), sb::append);
		}
		
		sb.append(')');
	}
	
}
