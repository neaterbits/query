package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Short_Collector_Single_Mapped_Alias_TypedJoin<MODEL, RESULT, JOIN_TYPE> 

		extends Short_Collector_Single_Mapped_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
		
		implements IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>,
				   ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT> {

	Short_Collector_Single_Mapped_Alias_TypedJoin(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> last) {
		super(last);
	}
	
	@Override
	public ISharedFunctions_Transform_Initial_Alias<
				MODEL,
				RESULT,
				ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>, 
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Byte, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, BigInteger, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Float, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>, 
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>> where() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedFunctions_Transform_Initial_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,
			
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>
	
		> and() {
		return andAlias();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>, 

			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>
		> or() {
		return orAlias();
	}
}
