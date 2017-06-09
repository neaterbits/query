package com.neaterbits.query.sql.dsl.api;

abstract class Debug {

	static final boolean DEBUG_FUNCTION_CALLS = true;
	static final boolean DEBUG_COLLECTOR_CALLS = true;
	static final boolean DEBUG_CONDITION_CALLS = true;

	final void enterFunctionCalls(String message) {
		if (DEBUG_FUNCTION_CALLS) {
			// Added function calls
			debug(null, message, true);
		}
	}

	final void exitFunctionCalls(Object result) {
		if (DEBUG_FUNCTION_CALLS) {
			// Added function calls
			debug(result, "", false);
		}
	}

	final void exitFunctionCalls() {
		if (DEBUG_FUNCTION_CALLS) {
			// Added function calls
			debug(null, "", false);
		}
	}

	final void enterCollectorCalls(String message) {
		if (DEBUG_COLLECTOR_CALLS) {
			// Added function calls
			debug(null, message, true);
		}
	}

	final void exitCollectorCalls(Object result) {
		if (DEBUG_COLLECTOR_CALLS) {
			// Added function calls
			debug(result, "", false);
		}
	}

	final void exitCollectorCalls() {
		if (DEBUG_COLLECTOR_CALLS) {
			// Added function calls
			debug(null, "", false);
		}
	}

	final void enterConditionCalls(String message) {
		if (DEBUG_CONDITION_CALLS) {
			// Added function calls
			debug(null, message, true);
		}
	}

	final void exitConditionCalls(Object result) {
		if (DEBUG_CONDITION_CALLS) {
			// Added function calls
			debug(result, "", false);
		}
	}

	final void exitConditionCalls() {
		if (DEBUG_CONDITION_CALLS) {
			// Added function calls
			debug(null, "", false);
		}
	}

	private void debug(Object result, String message, boolean enter) {

		// Find current function
		final StackTraceElement [] elements = Thread.currentThread().getStackTrace();

		final int level = 3;
		
		final String method = elements[level].getMethodName();
		final String className = elements[level].getClassName();
		final int lineNumber = elements[level].getLineNumber();

		final int simpleClassNameIndex = className.lastIndexOf('.');

		if (simpleClassNameIndex < 0) {
			throw new IllegalStateException("simpleClassNameIndex > 0");
		}

		final String simpleClassName = className.substring(simpleClassNameIndex + 1);

		final String type = "[" + (enter ? "ENTER" : "EXIT") + "]";

		System.out.println(
				"## " + type + " " + simpleClassName + ":" + lineNumber + "." + method + "() : " + message + 
						(enter
							? ""
							: " = " + (result != null ? result : "null"))
		);
	}
}
