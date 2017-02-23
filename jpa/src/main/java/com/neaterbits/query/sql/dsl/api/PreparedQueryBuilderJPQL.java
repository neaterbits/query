package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;



final class PreparedQueryBuilderJPQL<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>>
		extends PreparedQueryBuilderJPA<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> {

	PreparedQueryBuilderJPQL(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil, EntityManager em) {
		super(entityModelUtil, em, new QueryDialect_JPQL());
	}

	@Override
	final ConditionStringBuilder makeConditionStringBuilder(QueryParametersDistinct distinctParams) {
		return new ConditionStringBuilder_JPQL(distinctParams);
	}
}
