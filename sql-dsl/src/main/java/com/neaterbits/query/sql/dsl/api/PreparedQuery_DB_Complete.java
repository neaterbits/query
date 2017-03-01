package com.neaterbits.query.sql.dsl.api;



final class PreparedQuery_DB_Complete<QUERY> extends PreparedQuery_DB<QUERY> {

	private final QueryRunner queryRunner;
	
	PreparedQuery_DB_Complete(QueryDataSource_DB dataSource, QueryRunner queryRunner, ExecutableQuery<QUERY> queryAccess, QUERY query, QueryParametersDistinct distinctParams) {
		super(dataSource, queryAccess, query, distinctParams);
		
		this.queryRunner = queryRunner;
	}

	@Override
	final Object execute(ParamValueResolver paramCollector) {
		return executeWithParams(queryRunner, paramCollector);
	}
	
	@Override
	final void initParams(QueryRunner queryRunner, ParamValueResolver paramCollector) {
		
		// Since this is a completely resolved query, that means that all parameters could be resolved to a :paramXyz
		// thus we can iterate over all params

		if (distinctParams != null) {

			distinctParams.forEach((Param<?> param, Integer idx) -> {

				final BaseParamImpl<?> impl = (BaseParamImpl<?>)param;
				final Object value = paramCollector.resolveParam(param);

				queryRunner.setParam(impl, idx, value);
			});
		}
	}
}
