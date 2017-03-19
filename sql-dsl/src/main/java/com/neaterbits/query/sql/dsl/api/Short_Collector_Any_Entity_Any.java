package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Any_Entity_Any<
			MODEL, 
			RESULT,
			
			NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
			
			NAMED_MAPPED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
			ALIAS_MAPPED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
			
			
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
			
			
	extends Short_Collector_WhereOrJoin_Base<
			MODEL,
			RESULT,
			
			NAMED_WHERE_OR_JOIN,
			ALIAS_WHERE_OR_JOIN,
			
			NAMED_AND_CLAUSES, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_JOIN_CONDITION, NAMED_AND_OR,
			
			ALIAS_AND_CLAUSES, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_JOIN_CONDITION, ALIAS_AND_OR,
			
			AFTER_GROUP_BY> 
	{

	Short_Collector_Any_Entity_Any(Collector_Query<MODEL> queryCollector, Collector_Clause clauseCollector) {
		super(queryCollector, clauseCollector);
	}
}
