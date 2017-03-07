package com.neaterbits.query.sql.dsl.api;

final class CompiledFieldReference extends CompiledQueryElement<CollectedItem> {

	private final TypeMapSource source;
	private final CompiledGetter getter;
	
	final Object getValue(Object instance) {
		return getter.execute(instance);
	}

	CompiledFieldReference(CollectedItem original, TypeMapSource source, CompiledGetter getter) {

		super(original);

		/* not necessary and not part of FieldExpression
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		*/

		if (source == null) {
			throw new IllegalArgumentException("source == null");
		}

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.source = source;
		this.getter = getter;
	}

	TypeMapSource getSource() {
		return source;
	}

	CompiledGetter getGetter() {
		return getter;
	}
}
