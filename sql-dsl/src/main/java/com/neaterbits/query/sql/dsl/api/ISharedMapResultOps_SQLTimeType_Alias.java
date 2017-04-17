package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapResultOps_SQLTimeType_Alias<
		MODEL,
		RESULT,
		R, // extends Comparable<R>,
		RET extends ISharedFunction_After<MODEL, RESULT>>

	extends
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedResultMapperTo<MODEL, RESULT, R, RET> // Can always do "to" instead of op

{



}
