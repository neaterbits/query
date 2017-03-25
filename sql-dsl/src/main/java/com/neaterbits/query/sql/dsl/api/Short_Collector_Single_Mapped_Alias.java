package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;

final class Short_Collector_Single_Mapped_Alias<MODEL, RESULT> 
	
		extends Short_Collector_Single_Mapped_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
		implements 
				IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
				// when returned 'this' after where
				ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT> {

	Short_Collector_Single_Mapped_Alias(BaseQuery select, CollectedQueryResult_Mapped_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}
	
	Short_Collector_Single_Mapped_Alias(BaseQuery select, CollectedQueryResult_Entity_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}

	/*
	@Override
	public ISharedFunctions_Alias_Initial<
			MODEL, 
			RESULT,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>> where() {
		return whereAlias();
	}
	*/

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {

		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}

	@Override
	public ISharedMapFunctions_All_Alias<
				MODEL,
				RESULT,
				
				IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
				
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Date, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				
				ISharedResultOps_String_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Date, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>

		> map() {

		throw new UnsupportedOperationException("TODO");
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
				ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>
	
		> where() {

		throw new UnsupportedOperationException("TODO");
	}
}
