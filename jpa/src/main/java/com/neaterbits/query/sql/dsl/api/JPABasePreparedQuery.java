package com.neaterbits.query.sql.dsl.api;

import javax.persistence.NoResultException;
import javax.persistence.Query;

abstract class JPABasePreparedQuery implements DSPreparedQuery {

	private final QueryResultMode resultMode;
	private final ParamNameAssigner paramNameAssigner;

	JPABasePreparedQuery(QueryResultMode resultMode, ParamNameAssigner paramNameAssigner) {
		
		if (resultMode == null) {
			throw new IllegalArgumentException("resultMode == null");
		}

		if (paramNameAssigner == null) {
			throw new IllegalArgumentException("paramNameAssigner == null");
		}
		
		this.resultMode = resultMode;
		this.paramNameAssigner = paramNameAssigner;
	}

	String getNameForParam(Param<?> param) {
		return paramNameAssigner.getExistingName(param);
	}
	
	final Object executeWithParams(Query jpaQuery, ParamValueResolver paramCollector) {
		paramNameAssigner.forEach((Param<?> param, String name) -> {
			
			jpaQuery.setParameter(name, paramCollector.resolveParam(param));
			
		});

		Object ret;
		
		switch (resultMode) {
		case SINGLE:
			try {
				ret = jpaQuery.getSingleResult();
			}
			catch (NoResultException ex) {
				ret = null;
			}
			break;
			
			
		case MULTI:
			ret = jpaQuery.getResultList();
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown result mode " + resultMode);
		}
		
		return ret;
	}
}
