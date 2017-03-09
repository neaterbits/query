package com.neaterbits.query.sql.dsl.api;

final class ResultMapperOps_Numeric_Initial< 
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		RET extends ISharedFunction_After<MODEL, RESULT>>

	extends ResultMapperOps_Numeric<
			MODEL,
			RESULT,
			R,
			RET,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, R, RET>
			>

	implements ISharedResultOps_Numeric_Named<MODEL, RESULT, R, RET>

{
	// TODO go over constructor calls and use static utility methods? 
	ResultMapperOps_Numeric_Initial(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, impl);
	}
}
