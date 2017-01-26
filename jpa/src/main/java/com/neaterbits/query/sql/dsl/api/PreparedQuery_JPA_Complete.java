package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class PreparedQuery_JPA_Complete<QUERY> extends PreparedQuery_JPA_Base<QUERY> {

	private final javax.persistence.Query jpaQuery;

	PreparedQuery_JPA_Complete(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess, QUERY query, QueryParametersDistinct distinctParams, Query jpaQuery) {
		super(dataSource, queryAccess, query, distinctParams);
		
		if (jpaQuery == null) {
			throw new IllegalArgumentException("jpaQuery == null");
		}
		
		this.jpaQuery = jpaQuery;
	}

	@Override
	final void initParams(Query ormQuery, ParamValueResolver paramCollector) {
		
		// Since this is a completely resolved query, that means that all parameters could be resolved to a :paramXyz
		// thus we can iterate over all params

		if (distincParams != null) {
			
			distincParams.forEach((Param<?> param, Integer idx) -> {
				
				final String name = ConditionStringBuilder_JPA.makeParamName(idx);
				
				ormQuery.setParameter(name, paramCollector.resolveParam(param));
				
			});
		}
	}
	
	@Override
	Object execute(ParamValueResolver paramCollector) {

		return executeWithParams(jpaQuery, paramCollector);
	}
}
