package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_SingleResult_Decided_Named<MODEL, RESULT>
		extends Short_Collector_SingleResult_Decided<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>

		implements IShortResult_Mapped_Single_Named<MODEL, RESULT>,
				
				// when returned 'this' after where
				ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT> {

	Short_Collector_SingleResult_Decided_Named(CollectedQueryResult_Mapped_Single result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}

	Short_Collector_SingleResult_Decided_Named(CollectedQueryResult_Entity_Single result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}

	@Override
	public ISharedFunctions_Named_Initial<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>> where() {
		return whereNamed();
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(
			Collector_Base<MODEL> last, 
			int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}

	@Override
	public ISharedFunctions_Named_Initial<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>> and() {
		return andNamed();
	}

	@Override
	public ISharedFunctions_Named_Initial<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>> or() {
		return orNamed();
	}
}
