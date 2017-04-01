package com.neaterbits.query.sql.dsl.api;

@Deprecated // for now
final class ClassicCollector_AggregateNamedResult<TYPE>


		extends Classic_Collector_AggregateResult<
			SingleBuilt<TYPE>,
			TYPE,
			ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<SingleBuilt<TYPE>, TYPE>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<SingleBuilt<TYPE>, TYPE>>


		implements IClassicResult_Numeric_Named<TYPE> {

	ClassicCollector_AggregateNamedResult(ClassicSelect select, CollectedQueryResult_Aggregate result, ModelCompiler<SingleBuilt<TYPE>> modelCompiler) {
		super(select, result, modelCompiler);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<SingleBuilt<TYPE>, TYPE> createWhereOrJoinForNamed() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Named<SingleBuilt<TYPE>, TYPE>(getThisInitial());
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<SingleBuilt<TYPE>, TYPE> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Alias<SingleBuilt<TYPE>, TYPE>(getThisInitial());
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("TODO - entity/single?");
	}
}
