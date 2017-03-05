package com.neaterbits.query.sql.dsl.api;

final class CompiledMapping {

	private final CompiledFieldReference field;
	private final CompiledSetter setter;
	private final CollectedFunctions functions;
	
	CompiledMapping(CompiledFieldReference field, CompiledSetter setter, CollectedFunctions functions) {
		
		if (field == null) {
			throw new IllegalArgumentException("field == null");
		}

		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		this.field = field;
		this.setter = setter;
		this.functions = functions;
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

	int getNumFunctions() {
		return functions == null ? 0 : functions.getFunctions().size();
	}

	FunctionBase getFunctionAt(int idx) {
		return functions.getFunctions().get(idx);
	}
}
