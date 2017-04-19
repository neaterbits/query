package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Short_Collector_Initial_Single_Aggregate_Alias<RESULT> 
	extends Short_Collector_Initial_Single_Aggregate_Any<SingleBuilt<RESULT>, RESULT> 

	implements IShortBuilt_Numeric_Alias<RESULT>,
 		ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT> {

	Short_Collector_Initial_Single_Aggregate_Alias(BaseQuery query, ModelCompiler<SingleBuilt<RESULT>> modelCompiler, CollectedQueryResult_Aggregate result) {
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
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM> namedTypedJoinCollector() {
		throw new UnsupportedOperationException("N/A");
	}
	
	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Alias<SingleBuilt<RESULT>, RESULT> aliasTypedJoinCollector() {
		return new Short_Collector_Single_Aggregate_Alias_TypedJoin<>(getThisInitial());
	}

	@Override
	public ISharedFunctions_Transform_Initial_Alias<
			SingleBuilt<RESULT>,
			RESULT,
			ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Byte, 	 	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Short, 	 	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, 	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Long, 	 	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>, 
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigInteger, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>, 
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Float, 	 	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>, 
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Double,   	ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_String_All<SingleBuilt<RESULT>, RESULT, ISQLLogical_AndOr_SingleResult_Alias<SingleBuilt<RESULT>, RESULT>>
	
			> where() {
		return whereAlias();
	}


	@Override
	public ISharedFunctions_Transform_Initial_Alias<
			SingleBuilt<RESULT>,
			RESULT,
			ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Byte, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigInteger, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Float, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_String_All<SingleBuilt<RESULT>, RESULT, ISQLLogical_And_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>

			> and() {
		
		return andAlias();
	}


	@Override
	public ISharedFunctions_Transform_Initial_Alias<
			SingleBuilt<RESULT>,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Byte, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigInteger, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Float, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>,
			ISharedCondition_Comparable_String_All<SingleBuilt<RESULT>, RESULT, ISQLLogical_Or_NonProcessResult_Alias<SingleBuilt<RESULT>, RESULT>>

			> or() {

		return orAlias();
	}
}
