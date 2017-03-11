package com.neaterbits.query.sql.dsl.api;

final class AdhocDebug {

	static final boolean DEBUG_BUILD = false;
	static final boolean DEBUG_EXECUTE = false;

	static final boolean DEBUG_BUILD_QUERY = DEBUG_BUILD;
	static final boolean DEBUG_BUILD_CONDITIONS = DEBUG_BUILD;
	static final boolean DEBUG_EXECUTE_CONDITIONS = DEBUG_EXECUTE;

	static void println(String s) {
		
		final StackTraceElement st = Thread.currentThread().getStackTrace()[3];
		
		final String simpleClassName = st.getClassName().substring(st.getClassName().lastIndexOf('.') + 1);
		
		System.out.println(simpleClassName + "." + st.getMethodName() +  "() : " + s);
	}
}
