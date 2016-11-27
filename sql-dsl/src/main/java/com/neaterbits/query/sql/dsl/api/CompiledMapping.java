package com.neaterbits.query.sql.dsl.api;

final class CompiledMapping {

	private final CompiledGetter getter;
	private final CompiledSetter setter;
	
	CompiledMapping(CompiledGetter getter, CompiledSetter setter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		this.getter = getter;
		this.setter = setter;
	}

	CompiledGetter getGetter() {
		return getter;
	}

	CompiledSetter getSetter() {
		return setter;
	}
}
