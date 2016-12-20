package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class QueryDataSourcePojoWithInput extends QueryDataSourcePojoBase {

	private final ExecuteQueryPOJOsInput input;

	QueryDataSourcePojoWithInput(ExecuteQueryPOJOsInput input, QueryMetaModel queryMetaModel) {
		super(queryMetaModel);
		
		this.input = input;
	}

	@Override
	DSPreparedQuery prepareSingleQuery(CompiledQuery compiled) {
		return new PojoPreparedQuery(compiled, input, getQueryMetaModel());
	}

	@Override
	DSPreparedQuery prepareMultiQuery(CompiledQuery compiled) {
		return new PojoPreparedQuery(compiled, input, getQueryMetaModel());
	}
}
