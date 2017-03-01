package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

abstract class QueryRunner_JPA extends QueryRunner {

	final javax.persistence.Query jpaQuery;

	QueryRunner_JPA(Query jpaQuery) {
		
		if (jpaQuery == null) {
			throw new IllegalArgumentException("jpaQuery == null");
		}

		this.jpaQuery = jpaQuery;
	}
	
	@Override
	final Object executeForSingleResult() {
		
		Object ret;
		
		try {
			ret = jpaQuery.getSingleResult();
		}
		catch (NoResultException ex) {
			ret = null;
		}
		
		return ret;
	}

	@Override
	final List<?> executeForMultiResult() {
		return jpaQuery.getResultList();
	}
}
