package com.neaterbits.query.sql.dsl.api;

public interface IAdhocAndOrLogicalClauses<MODEL, RESULT, ENTITY>
		extends IAdhocAndClauses<MODEL, RESULT, ENTITY>,
				IAdhocOrClauses<MODEL, RESULT, ENTITY> {

}
