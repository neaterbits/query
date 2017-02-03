package com.neaterbits.query.sql.dsl.api;

final class Collector_AggregateNamedResult<TYPE> extends Collector_AggregateResult<SingleQuery<TYPE>, TYPE> 
		implements IClassicResult_Numeric_Named<TYPE> {

	Collector_AggregateNamedResult(QueryResultAggregate result, ModelCompiler<SingleQuery<TYPE>> modelCompiler) {
		super(result, modelCompiler);
	}
}
