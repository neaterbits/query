package com.neaterbits.query.sql.dsl.api;

public class CompiledFieldReference extends CompiledQueryElement<QueryBuilderItem> {
	private final CompiledSelectSource source;
	private final CompiledGetter getter;

	CompiledFieldReference(QueryBuilderItem original, CompiledSelectSource source, CompiledGetter getter) {

		super(original);

		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}

		if (source == null) {
			throw new IllegalArgumentException("source == null");
		}

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.source = source;
		this.getter = getter;
	}
	
	

}
