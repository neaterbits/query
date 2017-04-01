package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_Single_Mapped_Alias_TypedJoin<MODEL, RESULT, JOIN_TYPE> 

		extends Short_Collector_Single_Mapped_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
		
		implements IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> {

	Short_Collector_Single_Mapped_Alias_TypedJoin(BaseQuery select, CollectedQueryResult_Mapped_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
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
				ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>> where() {
		throw new UnsupportedOperationException("TODO");
	}
}
