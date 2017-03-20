package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_Multi_Entity_Named<MODEL, RESULT>
	extends Short_Collector_Multi_Entity_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> 


	implements ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>
{

	Short_Collector_Multi_Entity_Named(BaseQuery select, CollectedQueryResult_Entity_Multi result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}
	
	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
	

	@Override
	public ISharedFunctions_Transform_Initial_Named<
				MODEL,
				RESULT,
				ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short,
				ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>> 
	
			or() {
		
		return orNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL, 
			RESULT,
			ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>> and() {
		
		return andNamed();
	}
}
