package com.neaterbits.query.sql.dsl.api;

interface ISharedFunctions_Callback<MODEL, RESULT, RET extends ISharedFunction_After<MODEL, RESULT>> {

	ISharedFunction_Next<MODEL, RESULT, RET> onComparable(Expression expression);

	ISharedFunction_Next<MODEL, RESULT, RET> onString(Expression expression);
}

