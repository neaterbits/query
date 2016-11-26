package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

final class SingleQueryImpl<RESULT> extends BaseQueryImpl<RESULT> implements SingleQuery<RESULT> {
	
	SingleQueryImpl(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public RESULT execute(Collection<?>... collections) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public RESULT execute(QueryDataSource dataSource) {
		throw new UnsupportedOperationException("TODO");
	}
}
