package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_Single_Aggregate_Alias<RESULT> 
extends Short_Collector_Single_Aggregate_Any<SingleBuilt<RESULT>, RESULT> 

	implements IShortBuilt_Numeric_Alias<RESULT>,
 		ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT> {

	Short_Collector_Single_Aggregate_Alias(BaseQuery query, ModelCompiler<SingleBuilt<RESULT>> modelCompiler,
		QueryResultAggregate result) {
		super(query, modelCompiler, result);
	}
	
	
	/*
	@Override
	public ISharedFunctions_Transform_Initial_Alias<
			SingleBuilt<RESULT>,
			RESULT,
			
			ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>, 
			ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>, 
			ISharedCondition_Comparable_String_All_Compilable<SingleBuilt<RESULT>, RESULT, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>
	
	> where() {
		throw new UnsupportedOperationException("TODO");
	}
	*/
	
	@Override
	public ISharedFunctions_Transform_Initial_Alias<
			SingleBuilt<RESULT>,
			RESULT,
			ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Short, 	 	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, 	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Long, 	 	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>, 
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Double,   	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_String_All<SingleBuilt<RESULT>, RESULT, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>
	
			> where() {
		throw new UnsupportedOperationException("TODO");
	}
}
