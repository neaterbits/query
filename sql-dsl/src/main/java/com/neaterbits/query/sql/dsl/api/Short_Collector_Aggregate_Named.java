package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_Aggregate_Named<RESULT> 
		extends Short_Collector_Aggregate_Base<SingleBuilt<RESULT>, RESULT> 

		implements IShortBuilt_Numeric_Named<RESULT> {

	Short_Collector_Aggregate_Named(BaseQuery query, ModelCompiler<SingleBuilt<RESULT>> modelCompiler,
			QueryResultAggregate result) {
		super(query, modelCompiler, result);
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
				SingleBuilt<RESULT>,
				RESULT,
				
				ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>, 
				ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>, 
				ISharedCondition_Comparable_String_All_Compilable<SingleBuilt<RESULT>, RESULT, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>

		> where() {
		throw new UnsupportedOperationException("TODO");
	}
	
}
