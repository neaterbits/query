package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import javax.persistence.Query;

final class QueryRunner_JPA_JPQL extends QueryRunner_JPA {

	QueryRunner_JPA_JPQL(Query jpaQuery) {
		super(jpaQuery);
	}

	private static void setParamUtil(Query jpaQuery, BaseParamImpl<?> param, int idx, Object value) {
		final String name = ConditionStringBuilder_JPQL.makeParamName(idx);

		if (param.isList()) {
			
			final List<?> listVal = (List<?>)value;
			
			jpaQuery.setParameter(name, listVal);
		}
		else {
			jpaQuery.setParameter(name, value);
		}
	}
	
	@Override
	void setParam(BaseParamImpl<?> impl, int idx, Object value) {
		setParamUtil(jpaQuery, impl, idx, value);
	}
}
