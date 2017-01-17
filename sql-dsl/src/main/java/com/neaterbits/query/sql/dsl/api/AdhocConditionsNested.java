package com.neaterbits.query.sql.dsl.api;


final class AdhocConditionsNested<MODEL, RESULT, QUERY extends AdhocQueryNamed<MODEL, RESULT>>
		extends AdhocConditions<MODEL, RESULT, QUERY> {

	AdhocConditionsNested(QUERY query, int level) {
		super(query, level);
	}
}
