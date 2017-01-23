package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

abstract class PreparedQuery_JPA_Base<QUERY> extends PreparedQuery_DB<QUERY, javax.persistence.Query> {

	private final ParamNameAssigner paramNameAssigner;


	PreparedQuery_JPA_Base(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess, QUERY query, ParamNameAssigner paramNameAssigner) {
		super(dataSource, queryAccess, query);

		this.paramNameAssigner = paramNameAssigner;
	}

	final String getNameForParam(Param<?> param) {
		return paramNameAssigner.getExistingName(param);
	}
	
	
	@Override
	final void initParams(Query ormQuery, ParamValueResolver paramCollector) {
		if (paramNameAssigner != null) {
			paramNameAssigner.forEach((Param<?> param, String name) -> {
				
				ormQuery.setParameter(name, paramCollector.resolveParam(param));
				
			});
		}
	}

	@Override
	final Object executeForSingleResult(Query ormQuery) {
		
		Object ret;
		
		try {
			ret = ormQuery.getSingleResult();
		}
		catch (NoResultException ex) {
			ret = null;
		}
		
		return ret;
	}

	@Override
	final List<?> executeForMultiResult(Query ormQuery) {
		return ormQuery.getResultList();
	}
}
