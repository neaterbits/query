package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

abstract class FunctionCalcBase extends FunctionBase {

	abstract <T, R> R visit(FunctionVisitor<T, R> visitor, T param);

	abstract Object applyTo(ScalarType type, Object value);
}
