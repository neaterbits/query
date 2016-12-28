package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class AdhocConditionsSingular<MODEL, RESULT>
		extends AdhocConditions<MODEL, Object, AdhocQueryClassSingular<MODEL>>
	implements IAdhocAndOrLogicalClausesSingular<MODEL, Object> {

	AdhocConditionsSingular(AdhocQueryClassSingular<MODEL> query, int level) {
		super(query, level);
	}
}
