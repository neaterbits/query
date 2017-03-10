package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_ExpressionList_Base<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		SOURCE extends ISharedFunction_After<MODEL, RESULT>>

	extends CollectedItem {

	private final List<Expression> expressions;
	private final List<Operator> operators;

	// No previous expressions
	Collector_ExpressionList_Base() {
		this.expressions = new ArrayList<>();
		this.operators = new ArrayList<>();
	}

	Collector_ExpressionList_Base(Expression expression) {
		this();

		if (expression == null) {
			throw new IllegalArgumentException("expression == null");
		}

		// Add initial expression
		expressions.add(expression);
	}
	
	Collector_ExpressionList_Base(Collector_ExpressionList_Base<MODEL, RESULT, R, SOURCE> toCopy) {
		this.expressions = new ArrayList<>(toCopy.expressions);
		this.operators = new ArrayList<>(toCopy.operators);
	}

	final Expression collectExpressionListOrOne() {
		
		final Expression collected;
		
		if (expressions.size() == 1) {
			collected = expressions.get(0);
		}
		else {
			// multiple expressions, create list
			collected = new ExpressionList(expressions, operators);
		}

		return collected;
	}
	
	final ExpressionList collectAsExpressionList() {
		return new ExpressionList(expressions, operators);		
	}
	
	final void addField(Operator op, Function<?, ?> getter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		addField(op, new FieldExpression(getter));
	}

	final void addField(Operator op, Supplier<?> getter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		addField(op, new FieldExpression(getter));
	}
	
	private void addField(Operator op, FieldExpression expression) {
		if (op == null) {
			throw new IllegalArgumentException("op == null");
		}

		expressions.add(expression);
		operators.add(op);
	}
	
	
	final void addValue(Operator op, Comparable<?> value) {
		if (op == null) {
			throw new IllegalArgumentException("op == null");
		}

		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}

		expressions.add(new ValueExpression(value));
		operators.add(op);
	}

	final <RR extends Comparable<RR>> void addSubNumeric(Operator operator, ISharedSubOperandsFunction_Named<MODEL, RESULT, RR> builder) {
		
		final Expression expression = SubExpressionUtil.addSubNumericForOperator(operator, builder);
		
		expressions.add(expression);
		operators.add(operator);
	}

	final <RR extends Comparable<RR>> void addSubNumeric(Operator operator, ISharedSubOperandsFunction_Alias<MODEL, RESULT, RR> builder) {
		throw new UnsupportedOperationException("TODO");
	}
}
