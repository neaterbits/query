package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

abstract class GEN_TestCaseProvider {

	
	abstract void forAllTestCases(Consumer<GEN_TestCase> consumer);
	
	
}
