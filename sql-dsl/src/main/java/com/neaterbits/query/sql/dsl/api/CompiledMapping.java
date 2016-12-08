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
	
	final Object executeGetter(Object instance) {
		return field.getGetter().execute(instance);
	}

	final void executeSetter(Object instance, Object value) {
		setter.execute(instance, value);
	}
		
	CompiledFieldReference getField() {
		return field;
	}

	CompiledSetter getSetter() {
		return setter;
	}
}
