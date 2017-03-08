package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

abstract class ResultMapperOps<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>>

	extends CollectedItem
	implements ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> {
	
	private final IMappingCollector<MODEL, RESULT> impl;
	private final List<Expression> expressions;
	private final List<ArithmeticOperator> operators;
	
	ResultMapperOps(Expression expression, IMappingCollector<MODEL, RESULT> impl) {

		if (expression == null) {
			throw new IllegalArgumentException("expression == null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}

		this.impl = impl;

		this.expressions = new ArrayList<>();
		this.operators = new ArrayList<>();

		// Add initial expression
		expressions.add(expression);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final SOURCE to(BiConsumer<RESULT, R> setter) {

		final Expression toForward;

		if (expressions.size() == 1) {
			toForward = expressions.get(0);
		}
		else {
			// multiple expressions, create list
			toForward = new ExpressionList(expressions, operators);
		}

		impl.getMappingCollector().add(this, toForward, setter);

		return (SOURCE)impl;
	}

	final void addField(ArithmeticOperator op, Function<?, ?> getter) {
		
		if (op == null) {
			throw new IllegalArgumentException("op == null");
		}

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		expressions.add(new FieldExpression(getter));
		operators.add(op);
	}

	final void addValue(ArithmeticOperator op, Comparable<?> value) {
		if (op == null) {
			throw new IllegalArgumentException("op == null");
		}

		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}

		expressions.add(new ValueExpression(value));
		operators.add(op);
	}

	final <RR extends Comparable<RR>> void addSubNumeric(ArithmeticOperator operator, ISharedSubOperandsFunction_Named<MODEL, RESULT, RR> builder) {
		
		final Expression expression = SubExpressionUtil.addSubNumericForOperator(operator, builder, null);
		
		expressions.add(expression);
		operators.add(operator);
	}
}
