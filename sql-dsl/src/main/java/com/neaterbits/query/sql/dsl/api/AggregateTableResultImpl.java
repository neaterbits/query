package com.neaterbits.query.sql.dsl.api;

final class AggregateTableResultImpl<TYPE> extends AggregateResultImpl<SingleQuery<TYPE>, TYPE> 
		implements NumericTableResult<TYPE> {

	AggregateTableResultImpl(QueryResultAggregate result, ModelCompiler<SingleQuery<TYPE>> modelCompiler) {
		super(result, modelCompiler);
	}
}
