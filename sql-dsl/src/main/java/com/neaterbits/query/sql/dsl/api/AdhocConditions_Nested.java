package com.neaterbits.query.sql.dsl.api;


final class AdhocConditions_Nested<MODEL, RESULT, QUERY extends AdhocQuery_Named<MODEL, RESULT>>
		extends AdhocConditions<MODEL, RESULT, QUERY> {

	AdhocConditions_Nested(QUERY query, int level) {
		super(query, level);
	}
}
