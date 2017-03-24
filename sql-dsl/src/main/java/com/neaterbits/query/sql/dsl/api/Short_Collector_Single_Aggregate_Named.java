package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_Single_Aggregate_Named<RESULT> 
		extends Short_Collector_Single_Aggregate_Any<SingleBuilt<RESULT>, RESULT> 

		implements IShortBuilt_Numeric_Named<RESULT>,
		 ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT> {

	Short_Collector_Single_Aggregate_Named(BaseQuery query, ModelCompiler<SingleBuilt<RESULT>> modelCompiler,
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

	@Override
	public ISharedFunctions_Transform_Initial_Named<SingleBuilt<RESULT>, RESULT, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_String_All<SingleBuilt<RESULT>, RESULT, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>> and() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<SingleBuilt<RESULT>, RESULT, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, ISharedCondition_Comparable_String_All<SingleBuilt<RESULT>, RESULT, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>> or() {
		throw new UnsupportedOperationException("TODO");
	}
}
