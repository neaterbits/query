package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class FieldExpression extends Expression {

	private final Getter getter;

	// Short hand helpers
	
	FieldExpression(Function<?, ?> getter) {
		this(new FunctionGetter(getter));
	}

	FieldExpression(Supplier<?> getter) {
		this(new SupplierGetter(getter));
	}
	
	FieldExpression(Getter getter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}

	Getter getGetter() {
		return getter;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onField(this, param);
	}
}
