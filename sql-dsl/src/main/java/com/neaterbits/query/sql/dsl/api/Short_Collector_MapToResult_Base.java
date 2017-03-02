package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_MapToResult_Base<
			MODEL, 
			RESULT,
			
			NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
			
			
			NAMED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
			NAMED_OR_CLAUSES  extends ISharedLogical_Or_Named_All <MODEL, RESULT, NAMED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,

			NAMED_NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
			NAMED_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,			

			NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION>,

			NAMED_AND_OR extends ISharedLogical_And_Or_Named_All<
				MODEL,
				RESULT,
				NAMED_AND_CLAUSES,
				NAMED_OR_CLAUSES,
				NAMED_NESTED_AND_CLAUSES,
				NAMED_NESTED_OR_CLAUSES>,
			
			ALIAS_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
			ALIAS_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,

			ALIAS_NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
			ALIAS_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_NESTED_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,			

			ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, ALIAS_JOIN_CONDITION>,

			ALIAS_AND_OR extends ISharedLogical_And_Or_Alias<
				MODEL,
				RESULT,
				ALIAS_AND_CLAUSES,
				ALIAS_OR_CLAUSES,
				ALIAS_NESTED_AND_CLAUSES,
				ALIAS_NESTED_OR_CLAUSES>,
				
				
			AFTER_GROUP_BY
			>


	extends SQL_Collector_WhereOrJoin_Base<
			MODEL,
			RESULT,
			
			NAMED_AND_CLAUSES, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_JOIN_CONDITION, NAMED_AND_OR, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>,
			
			ALIAS_AND_CLAUSES, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_JOIN_CONDITION, ALIAS_AND_OR, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>,

			AFTER_GROUP_BY
			>
	
	implements IMappingCollector<MODEL, RESULT>, ISharedSelectSourceBuilder<MODEL, RESULT> {
	
	Short_Collector_MapToResult_Base(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl<MODEL>(modelCompiler, result), new Collector_Clause(EConditionsClause.WHERE, ConditionsType.SINGLE));

		final MappingCollector mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
	}

	@Override
	public final MappingCollector getMappingCollector() {
		return getQueryCollector().getMappings();
	}
}
