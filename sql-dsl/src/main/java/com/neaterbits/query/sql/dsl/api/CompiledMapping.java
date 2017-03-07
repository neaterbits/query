package com.neaterbits.query.sql.dsl.api;

final class CompiledMapping {

	private final Expression expression;
	
	@Deprecated
	private final CompiledFieldReference field;
	private final CompiledSetter setter;
	@Deprecated
	private final CollectedFunctions functions;

	
	CompiledMapping(Expression expression, CompiledSetter setter) {
		
		if (expression == null) {
			throw new IllegalArgumentException("expression == null");
		}

		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		this.expression = expression;
		this.field = null;
		this.setter = setter;
		this.functions = null;
	}
	
	@Deprecated
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
		this.expression = null;
	}
	
	final Object executeGetter(Object instance) {
		return field.getGetter().execute(instance);
	}

	final void executeSetter(Object instance, Object value) {
		setter.execute(instance, value);
	}

	Expression getExpression() {
		return expression;
	}

	@Deprecated
	CompiledFieldReference getField() {
		return field;
	}

	CompiledSetter getSetter() {
		return setter;
	}

	@Deprecated
	int getNumFunctions() {
		return functions == null ? 0 : functions.getFunctions().size();
	}

	@Deprecated
	FunctionBase getFunctionAt(int idx) {
		return functions.getFunctions().get(idx).getFunction();
	}
}
