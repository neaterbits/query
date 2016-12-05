package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

abstract class JPABasePreparedQuery implements DSPreparedQuery {

	private final boolean singleResult;
	private final ParamNameAssigner paramNameAssigner;

	JPABasePreparedQuery(boolean singleResult, ParamNameAssigner paramNameAssigner) {
		
		if (paramNameAssigner == null) {
			throw new IllegalArgumentException("paramNameAssigner == null");
		}
		
		this.singleResult = singleResult;
		this.paramNameAssigner = paramNameAssigner;
	}

	String getNameForParam(Param<?> param) {
		return paramNameAssigner.getExistingName(param);
	}
	
	final Object executeWithParams(Query jpaQuery, ParamValueResolver paramCollector) {
		paramNameAssigner.forEach((Param<?> param, String name) -> {
			
			jpaQuery.setParameter(name, paramCollector.resolveParam(param));
			
		});
		
		return singleResult ? jpaQuery.getSingleResult() : jpaQuery.getResultList();
	}
}
