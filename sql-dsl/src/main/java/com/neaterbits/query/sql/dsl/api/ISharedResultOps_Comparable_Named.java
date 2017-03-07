package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultOps_Comparable_Named<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		RET extends ISharedSelectSourceBuilder<MODEL, RESULT>>
		
		extends
			ISharedFunction_Next<MODEL, RESULT, RET>,
			ISharedResultMapperTo<MODEL, RESULT, R, RET> // Can always do "to" instead of op

{



}
