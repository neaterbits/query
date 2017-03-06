package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

abstract class FunctionCalcBase extends FunctionBase {

	abstract Object applyTo(ScalarType type, Object value);
}
