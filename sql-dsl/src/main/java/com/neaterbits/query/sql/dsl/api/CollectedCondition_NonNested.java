package com.neaterbits.query.sql.dsl.api;

abstract class CollectedCondition_NonNested extends CollectedCondition {

	private final Getter getter;
	private CollectedFunctions functions;

	abstract EClauseOperator getOperator();

	CollectedCondition_NonNested(Getter getter) {

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}

	final Getter getGetter() {
		return getter;
	}

	final CollectedFunctions getFunctions() {
		return functions;
	}

	final void setFunctions(CollectedFunctions functions) {

		if (functions == null) {
			throw new IllegalArgumentException("functions == null");
		}

		if (this.functions != null) {
			throw new IllegalStateException("functions already set");
		}

		this.functions = functions;
	}
}
