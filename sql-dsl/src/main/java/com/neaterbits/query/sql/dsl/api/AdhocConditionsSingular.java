package com.neaterbits.query.sql.dsl.api;

final class AdhocConditionsSingular<MODEL, RESULT>
		extends AdhocConditions<MODEL, Object, AdhocQueryClassSingular<MODEL>>
	implements IAdhocAndOrLogicalClausesSingular<MODEL, Object> {

	AdhocConditionsSingular(AdhocQueryClassSingular<MODEL> query, int level) {
		super(query, level);
	}
}
