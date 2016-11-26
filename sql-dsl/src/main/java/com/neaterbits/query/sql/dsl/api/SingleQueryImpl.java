package com.neaterbits.query.sql.dsl.api;

final class SingleQueryImpl<RESULT> extends BaseQueryImpl<RESULT> implements SingleQuery<RESULT> {
	
	SingleQueryImpl(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public RESULT executeOn(QueryDataSource dataSource) {
		throw new UnsupportedOperationException("TODO");
	}
}
