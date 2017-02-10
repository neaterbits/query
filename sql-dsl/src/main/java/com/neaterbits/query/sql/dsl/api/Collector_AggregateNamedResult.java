package com.neaterbits.query.sql.dsl.api;

final class Collector_AggregateNamedResult<TYPE>


		extends Collector_AggregateResult<
			SingleQuery<TYPE>,
			TYPE,
			IClassicLogical_WhereOrJoin_SingleResult_Named<SingleQuery<TYPE>, TYPE>,
			IClassicLogical_WhereOrJoin_SingleResult_Alias<SingleQuery<TYPE>, TYPE>>


		implements IClassicResult_Numeric_Named<TYPE> {

	Collector_AggregateNamedResult(QueryResultAggregate result, ModelCompiler<SingleQuery<TYPE>> modelCompiler) {
		super(result, modelCompiler);
	}

	@Override
	IClassicLogical_WhereOrJoin_SingleResult_Named<SingleQuery<TYPE>, TYPE> createWhereOrJoinForNamed() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Named<SingleQuery<TYPE>, TYPE>(this);
	}

	@Override
	IClassicLogical_WhereOrJoin_SingleResult_Alias<SingleQuery<TYPE>, TYPE> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Alias<SingleQuery<TYPE>, TYPE>(this);
	}
}
