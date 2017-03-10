package com.neaterbits.query.sql.dsl.api;

@Deprecated // no need for named / alias since returnes expressions
public interface ISharedFunctions_Callback_Named<MODEL, RESULT, RET extends ISharedFunction_After<MODEL, RESULT>> {

	ISharedFunction_Next<MODEL, RESULT, RET> onComparable(Expression expression);

	ISharedFunction_Next<MODEL, RESULT, RET> onString(Expression expression);
}

