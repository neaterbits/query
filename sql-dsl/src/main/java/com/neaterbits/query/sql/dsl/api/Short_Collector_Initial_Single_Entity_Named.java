package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Short_Collector_Initial_Single_Entity_Named<MODEL, RESULT>

	extends Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT> 

	implements IShortResult_Entity_Single_Named<MODEL, RESULT>,
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT> {

	Short_Collector_Initial_Single_Entity_Named(BaseQuery select, ModelCompiler<MODEL> modelCompiler, CollectedQueryResult_Entity_Single result, SharedSelectSource_Named selectSource) {
		super(select, modelCompiler, result, selectSource);
	}
	
	

	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> namedTypedJoinCollector() {
		throw new UnsupportedOperationException("N/A");
	}
	
	

	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> aliasTypedJoinCollector() {
		throw new UnsupportedOperationException("Not alias");
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>, 
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,

			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Byte, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigInteger, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Float, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>
	
			> where() {

		throw new UnsupportedOperationException("TODO");
	}



	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,

			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>
		> or() {
		return orNamed();
	}



	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,

			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>
		> and() {
		return andNamed();
	}
}
