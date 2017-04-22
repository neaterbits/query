package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

@Deprecated // there is a concat operand
final class Function_String_Concat extends Function_String {

	static final Function_String_Concat INSTANCE = new Function_String_Concat();
	
	private Function_String_Concat() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onStringConcat(this, param);
	}

	@Override
	Object applyTo(ScalarType type, Object value) {
		
		if (type != ScalarType.STRING) {
			throw new UnsupportedOperationException("Unknown datatype " + type);
		}

		throw new UnsupportedOperationException("TODO: need multiple values here probably");
	}
	
	/*
	private final ConditionValue lhs;
	private final ConditionValue rhs;
	
	<T> Function_String_Concat(IFunctionString<T> getter1, IFunctionString<T> getter2) {
		
		if (getter1 == null) {
			throw new IllegalArgumentException("getter1 == null");
		}

		if (getter2 == null) {
			throw new IllegalArgumentException("getter2 == null");
		}
		
		this.lhs = new ConditionValue_Getter(new FunctionGetter(getter1));
		this.rhs = new ConditionValue_Getter(new FunctionGetter(getter2));
	}
	
	<T> Function_String_Concat(IFunctionString<T> getter, String value) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		this.lhs = new ConditionValue_Getter(new FunctionGetter(getter));
		this.rhs = new ConditionValue_Literal_String(value);
	}

	<T> Function_String_Concat(String value, IFunctionString<T> getter) {

		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.lhs = new ConditionValue_Literal_String(value);
		this.rhs = new ConditionValue_Getter(new FunctionGetter(getter));
	}

    <T> Function_String_Concat(IFunctionString<T> getter, Param<String> param) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		this.lhs = new ConditionValue_Getter(new FunctionGetter(getter));
		this.rhs = new ConditionValue_Param(param);
    }

    <T> Function_String_Concat(Param<String> param, IFunctionString<T> getter) {

		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.lhs = new ConditionValue_Param(param);
		this.rhs = new ConditionValue_Getter(new FunctionGetter(getter));
    }

	@Override
	Object applyTo(ScalarType type, Object value) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onStringConcat(this, param);
	}
	*/
}
