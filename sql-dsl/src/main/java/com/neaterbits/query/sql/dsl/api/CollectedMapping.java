package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

abstract class CollectedMapping extends CollectedItem {
	private final Expression expression;
	private final CollectedItem original;

	@Deprecated
	private final Getter getter;
	private final BiConsumer<?, ?> setter;

	
	@Deprecated
	private final CollectedFunctions functions;

	CollectedMapping(CollectedItem original, Expression expression, BiConsumer<?, ?> setter) {
		if (expression == null) {
			throw new IllegalArgumentException("expression == null");
		}
		
		this.original = original;
		this.expression = expression;
		this.setter = setter;
		this.getter = null;
		this.functions = null;
	}
	
	@Deprecated
	CollectedMapping(CollectedItem original, Getter getter, BiConsumer<?, ?> setter, CollectedFunctions functions) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		this.original = original;
		this.getter = getter;
		this.setter = setter;
		this.functions = functions;
		this.expression = null;
	}

	@Deprecated
	final Getter getGetter() {
		return getter;
	}
	
	final Expression getExpression() {
		return expression;
	}

	final CollectedItem getOriginal() {
		return original;
	}

	final BiConsumer<?, ?> getSetter() {
		return setter;
	}

	@Deprecated
	final CollectedFunctions getFunctions() {
		return functions;
	}

	/*
	final void setFunctions(CollectedFunctions functions) {

		if (functions == null) {
			throw new IllegalArgumentException("functions == null");
		}

		if (this.functions != null) {
			throw new IllegalStateException("functions already set");
		}

		this.functions = functions;
	}
	*/
}
