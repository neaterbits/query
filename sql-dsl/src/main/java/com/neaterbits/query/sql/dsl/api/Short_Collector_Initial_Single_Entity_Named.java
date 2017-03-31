package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public class Short_Collector_Initial_Single_Entity_Named<MODEL, RESULT>

	extends Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT> 

	implements IShortResult_Entity_Single_Named<MODEL, RESULT> {

	Short_Collector_Initial_Single_Entity_Named(BaseQuery select, SharedSelectSource_Named selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(select, selectSource, modelCompiler);
	}

	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> namedTypedJoinCollector() {
		throw new UnsupportedOperationException("Not named");
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>, 
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>
	
			> where() {

		throw new UnsupportedOperationException("TODO");
	}
}
