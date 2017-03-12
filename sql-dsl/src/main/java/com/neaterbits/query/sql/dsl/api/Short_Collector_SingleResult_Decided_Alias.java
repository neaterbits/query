package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_SingleResult_Decided_Alias<MODEL, RESULT> 
	
		extends Short_Collector_SingleResult_Decided<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
		implements 
				IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
				// when returned 'this' after where
				ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT> {

	Short_Collector_SingleResult_Decided_Alias(BaseQuery select, CollectedQueryResult_Mapped_Single result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);
	}
	
	Short_Collector_SingleResult_Decided_Alias(BaseQuery select, CollectedQueryResult_Entity_Single result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);
	}

	/*
	@Override
	public ISharedFunctions_Alias_Initial<
			MODEL, 
			RESULT,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>> where() {
		return whereAlias();
	}
	*/

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {

		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}

	@Override
	public ISharedMapFunctions_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>, ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>, ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>, ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>, ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>, ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>, ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>, ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>, ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>> map() {
		throw new UnsupportedOperationException("TODO");
	}
}
