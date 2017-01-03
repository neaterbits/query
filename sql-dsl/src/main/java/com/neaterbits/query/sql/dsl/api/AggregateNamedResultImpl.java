package com.neaterbits.query.sql.dsl.api;

final class AggregateNamedResultImpl<TYPE> extends AggregateResultImpl<SingleQuery<TYPE>, TYPE> 
		implements IClassicNumericNamedResult<TYPE> {

	AggregateNamedResultImpl(QueryResultAggregate result, ModelCompiler<SingleQuery<TYPE>> modelCompiler) {
		super(result, modelCompiler);
	}
}
