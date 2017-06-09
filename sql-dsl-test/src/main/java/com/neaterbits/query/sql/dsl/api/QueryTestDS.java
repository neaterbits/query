package com.neaterbits.query.sql.dsl.api;

public abstract class QueryTestDS implements QueryTestDSStore {

	private static final boolean DEBUG = false;

	protected static final void debug(String s) {

		if (DEBUG) {
			System.out.println("## " + s);
		}
	}
}
