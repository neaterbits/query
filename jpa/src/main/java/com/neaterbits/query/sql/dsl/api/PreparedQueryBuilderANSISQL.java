package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;

final class PreparedQueryBuilderANSISQL<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>>
		extends PreparedQueryBuilderJPA<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> {


	PreparedQueryBuilderANSISQL(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil, EntityManager em) {
		super(entityModelUtil, em, new QueryDialect_ANSI_SQL<>(entityModelUtil));
		
	}

	@Override
	final ConditionStringBuilder makeConditionStringBuilder(QueryParametersDistinct distinctParams) {
		return new ConditionStringBuilder_Native(distinctParams);
	}
	
}
