package com.neaterbits.query.sql.dsl.api;

import java.util.Stack;

// Whether available for one particular test
@FunctionalInterface
interface GEN_TestCase_Applicability {

	boolean isApplicable(Stack<GEN_Functionality> stack); 

}
