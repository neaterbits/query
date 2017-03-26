package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_Initial_Single_Entity_Alias<MODEL, RESULT> 
		extends Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT> 

		implements IShortResult_Entity_Single_Alias<MODEL, RESULT> {


	Short_Collector_Initial_Single_Entity_Alias(BaseQuery select, SharedSelectSource_Alias selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(select, selectSource, modelCompiler);
	}

	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> namedTypedJoinCollector() {
		throw new UnsupportedOperationException("Not named");
	}

	@Override
	public ISharedFunctions_Transform_Initial_Alias<
	
			MODEL,
			RESULT,

			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>

		> where() {
		throw new UnsupportedOperationException("TODO");
	}
}