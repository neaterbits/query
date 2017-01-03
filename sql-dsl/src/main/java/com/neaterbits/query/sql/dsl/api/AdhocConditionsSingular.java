package com.neaterbits.query.sql.dsl.api;

final class AdhocConditionsSingular<MODEL, RESULT>
		extends AdhocConditions<MODEL, Object, AdhocQueryNamedSingular<MODEL>>
	implements IAdhocAndOrLogicalClausesSingular<MODEL, Object> {

	AdhocConditionsSingular(AdhocQueryNamedSingular<MODEL> query, int level) {
		super(query, level);
	}
}
