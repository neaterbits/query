package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Short_Collector_Initial_Single_Entity_Alias<MODEL, RESULT> 
		extends Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT> 

		implements IShortResult_Entity_Single_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT> {


	Short_Collector_Initial_Single_Entity_Alias(BaseQuery select, ModelCompiler<MODEL> modelCompiler, CollectedQueryResult_Entity_Single result, SharedSelectSource_Alias selectSource) {
		super(select, modelCompiler, result, selectSource);
	}

	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> namedTypedJoinCollector() {
		throw new UnsupportedOperationException("Not named");
	}
	
	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> aliasTypedJoinCollector() {
		return new Short_Collector_Single_Entity_Alias_TypedJoin<>(this);
	}

	@Override
	public ISharedFunctions_Transform_Initial_Alias<
	
			MODEL,
			RESULT,

			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,

			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
			ISharedComparison_Comparable_String_All<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>

		> where() {
		
		return whereAlias();
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
}
