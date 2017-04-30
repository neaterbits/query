package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

abstract class Short_Collector_Single_Entity_Named_Base<MODEL, RESULT>

		extends Short_Collector_Single_Entity_Any<
			MODEL,
			RESULT,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>

		implements ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>

{

	Short_Collector_Single_Entity_Named_Base(
			Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> last,
			CollectedQueryResult_Entity_Single result) {
		super(last, result);
	}

	@Override
	public final ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>
		> and() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT, 
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,

			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>
	> or() {
		throw new UnsupportedOperationException("TODO");
	}
}
