package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledFunctionExpression extends CompiledExpression {

	private final FunctionExpressionBase<CompiledExpression> function;
	
	CompiledFunctionExpression(FunctionBase function, List<CompiledExpression> parameters) {
		this.function = new FunctionExpressionBase<>(function, parameters);
	}

	FunctionBase getFunction() {
		return function.getFunction();
	}
	
	
	CompiledFieldReference getField() {
		return function.getParameters().size() == 0
				? null
				: ((CompiledFieldExpression)function.getParameters().get(0)).getFieldReference();
	}
	

	@Override
	<T, R> R visit(CompiledExpressionVisitor<T, R> visitor, T param) {
		return visitor.onFunction(this, param);
	}
}
