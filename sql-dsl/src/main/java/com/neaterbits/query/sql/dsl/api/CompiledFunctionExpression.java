package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledFunctionExpression extends CompiledExpression {

	private final FunctionExpressionBase<CompiledExpression> function;
	
	CompiledFunctionExpression(FunctionBase function, List<CompiledExpression> parameters) {
		
		if (!parameters.isEmpty()) {
			final CompiledExpression fieldParam = parameters.get(0);
			
			if (!(fieldParam instanceof CompiledFieldExpression || fieldParam instanceof CompiledExpressionList)) {
				throw new IllegalStateException("Expected param to be field or sub: " + fieldParam.getClass().getSimpleName());
			}
		}
		
		this.function = new FunctionExpressionBase<>(function, parameters);
	}

	FunctionBase getFunction() {
		return function.getFunction();
	}
	
	List<CompiledExpression> getParameters() {
		return function.getParameters();
	}
	
	@Deprecated
	CompiledFieldReference getField() {

		final CompiledFieldReference ret;

		if (function.getParameters().isEmpty()) {
			ret = null;
		}
		else {
			final CompiledExpression initial = function.getParameters().get(0);

			if (initial instanceof CompiledFieldExpression) {
				ret = ((CompiledFieldExpression)initial).getFieldReference();
			}
			else {
				throw new IllegalStateException("Unknown initial field type " + initial.getClass().getSimpleName());
			}
		}

		return ret;
	}
	

	@Override
	<T, R> R visit(CompiledExpressionVisitor<T, R> visitor, T param) {
		return visitor.onFunction(this, param);
	}
}
