package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_MultiResult_Decided_Named<MODEL, RESULT>
		extends Short_Collector_MultiResult_Decided<MODEL, RESULT> 

	implements IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
			ISQLLogical_AndOr_MultiMapped_Named<MODEL, RESULT>{

	Short_Collector_MultiResult_Decided_Named(BaseShortSelect select, CollectedQueryResult_Mapped_Multi result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);
	}

	Short_Collector_MultiResult_Decided_Named(BaseShortSelect select, CollectedQueryResult_Entity_Multi result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}

	@Override
	public ISharedMapFunctions_Named<
			MODEL,
			RESULT,
			IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>
	
		> map() {
		
		
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>
			callback = MapFunctionUtil.multiNamedCallback(() -> this);
	
		return new Collector_MapFunctions_Named<
			
				MODEL,
				RESULT,

				IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
				
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>
	
				>(callback, null);
	}

	@Override
	public <LEFT, RIGHT> ISQLJoin_Condition_MultiMapped_Named<MODEL, RESULT, LEFT, RIGHT> innerJoin(
			Class<LEFT> leftType, Class<RIGHT> rightType) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <LEFT, RIGHT> ISQLJoin_Condition_MultiMapped_Named<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType,
			Class<RIGHT> rightType) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>>> and() {
		return andNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>>> or() {
		return orNamed();
	}
	
}
