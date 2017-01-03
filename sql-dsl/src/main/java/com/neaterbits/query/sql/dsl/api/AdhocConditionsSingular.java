package com.neaterbits.query.sql.dsl.api;

final class AdhocConditionsSingular<MODEL, RESULT>
		extends AdhocConditions<MODEL, Object, AdhocQueryNamed_Singular<MODEL>>
	implements IAdhocAndOrLogicalClausesSingular<MODEL, Object> {

	AdhocConditionsSingular(AdhocQueryNamed_Singular<MODEL> query, int level) {
		super(query, level);
	}
}
