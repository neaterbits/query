package com.neaterbits.query.sql.dsl.api;

final class CompiledMapping {

	private final CompiledFieldReference field;
	private final CompiledSetter setter;
	
	CompiledMapping(CompiledFieldReference field, CompiledSetter setter) {
		
		if (field == null) {
			throw new IllegalArgumentException("field == null");
		}

		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		this.field = field;
		this.setter = setter;
	}
	
	CompiledFieldReference getField() {
		return field;
	}

	CompiledSetter getSetter() {
		return setter;
	}
}
