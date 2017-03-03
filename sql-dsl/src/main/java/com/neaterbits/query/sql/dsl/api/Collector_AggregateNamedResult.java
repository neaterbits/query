package com.neaterbits.query.sql.dsl.api;

@Deprecated // for now
final class Collector_AggregateNamedResult<TYPE>


		extends Classic_Collector_AggregateResult<
			SingleCompiled<TYPE>,
			TYPE,
			ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<SingleCompiled<TYPE>, TYPE>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<SingleCompiled<TYPE>, TYPE>>


		implements IClassicResult_Numeric_Named<TYPE> {

	Collector_AggregateNamedResult(QueryResultAggregate result, ModelCompiler<SingleCompiled<TYPE>> modelCompiler) {
		super(result, modelCompiler);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<SingleCompiled<TYPE>, TYPE> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Named<SingleCompiled<TYPE>, TYPE>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<SingleCompiled<TYPE>, TYPE> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Alias<SingleCompiled<TYPE>, TYPE>(this);
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("TODO - entity/single?");
	}
}
