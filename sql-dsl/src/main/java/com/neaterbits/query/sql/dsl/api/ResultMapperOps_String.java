package com.neaterbits.query.sql.dsl.api;

abstract class ResultMapperOps_String<
		MODEL,
		RESULT,
		AFTER extends ISharedFunction_After<MODEL, RESULT>,
		NEXT extends ISharedFunction_Next<MODEL, RESULT, AFTER>>

	extends ResultMapperOps<MODEL, RESULT, String, AFTER> 

	implements ISharedOperands_String_Named<MODEL, RESULT, AFTER, NEXT> {

	
	ResultMapperOps_String(Expression expression, boolean sub) {
		super(expression, sub);
	}

	ResultMapperOps_String(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, impl);
	}

	@Override
	public final <T> NEXT concat(StringFunction<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <T> NEXT concat(String value) {
		throw new UnsupportedOperationException("TODO");
	}
}
