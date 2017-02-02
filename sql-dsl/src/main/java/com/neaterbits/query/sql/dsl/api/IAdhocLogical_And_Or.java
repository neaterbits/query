package com.neaterbits.query.sql.dsl.api;

public interface IAdhocLogical_And_Or<MODEL, RESULT, ENTITY>
		extends
				ISharedLogical_Base<MODEL, RESULT>,
				IAdhocLogical_And<MODEL, RESULT, ENTITY>,
				IAdhocLogical_Or<MODEL, RESULT, ENTITY> {

}
