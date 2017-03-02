package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class Short_Collector_MapToResult_Single<MODEL, RESULT> 
	extends Short_Collector_MapToResult_Base<
			MODEL,
			RESULT,
			ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL,RESULT>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL,RESULT>> 

	implements IShortResult_Mapped_Single_All<MODEL, RESULT> /*,
	
			IShortResult_Mapped_Single_Named<MODEL, RESULT>,
			IShortResult_Mapped_Single_Alias<MODEL, RESULT> */

{
	Short_Collector_MapToResult_Single(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
	
	
	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IShortResult_Mapped_Single_Named<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IShortResult_Mapped_Single_Alias<MODEL, RESULT>> map(Supplier<R> getter) {

		return new ResultMapperToImpl<>(getter, this);
	}

	
}
