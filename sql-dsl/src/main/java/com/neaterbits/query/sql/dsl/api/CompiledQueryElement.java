package com.neaterbits.query.sql.dsl.api;

abstract class CompiledQueryElement<T extends QueryBuilderItem> {

	private final T original;

	CompiledQueryElement(T original) {
		this.original = original;
	}

	final T getOriginal() {
		return original;
	}
}
