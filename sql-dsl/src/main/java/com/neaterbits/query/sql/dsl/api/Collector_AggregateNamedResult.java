package com.neaterbits.query.sql.dsl.api;

final class Collector_AggregateNamedResult<TYPE>


		extends Collector_AggregateResult<
			SingleQuery<TYPE>,
			TYPE,
			ISQLLogical_WhereOrJoin_SingleResult_Named<SingleQuery<TYPE>, TYPE>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias<SingleQuery<TYPE>, TYPE>>


		implements IClassicResult_Numeric_Named<TYPE> {

	Collector_AggregateNamedResult(QueryResultAggregate result, ModelCompiler<SingleQuery<TYPE>> modelCompiler) {
		super(result, modelCompiler);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named<SingleQuery<TYPE>, TYPE> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Named<SingleQuery<TYPE>, TYPE>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Alias<SingleQuery<TYPE>, TYPE> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Alias<SingleQuery<TYPE>, TYPE>(this);
	}
}
