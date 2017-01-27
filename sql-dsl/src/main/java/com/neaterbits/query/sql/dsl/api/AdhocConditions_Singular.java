package com.neaterbits.query.sql.dsl.api;

final class AdhocConditions_Singular<MODEL, RESULT>
		extends AdhocConditions<MODEL, Object, AdhocQuery_Named_Singular<MODEL>>
	implements IAdhocLogical_And_Or_Singular<MODEL, Object, Object> {

	AdhocConditions_Singular(AdhocQuery_Named_Singular<MODEL> query, int level) {
		super(query, level);
	}
}
