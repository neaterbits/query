package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.List;

final class MultiQueryImpl<RESULT> extends BaseQueryImpl<RESULT> implements MultiQuery<RESULT> {

	MultiQueryImpl(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public List<RESULT> execute(Collection<?>... collections) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public List<RESULT> execute(QueryDataSource dataSource) {
		throw new UnsupportedOperationException("TODO");
	}
}
