package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

abstract class PreparedQuery_JPA_Base<QUERY> extends PreparedQuery_DB<QUERY, javax.persistence.Query> {

	final QueryParametersDistinct distincParams;


	PreparedQuery_JPA_Base(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess, QUERY query, QueryParametersDistinct distinctParams) {
		super(dataSource, queryAccess, query);

		this.distincParams = distinctParams;
	}

	/*
	final String getNameForParam(Param<?> param) {
		return distincParams.getExistingName(param);
	}
	*/
	

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
