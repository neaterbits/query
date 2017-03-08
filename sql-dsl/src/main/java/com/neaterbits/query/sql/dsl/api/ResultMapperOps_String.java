package com.neaterbits.query.sql.dsl.api;

final class ResultMapperOps_String<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>>

	extends ResultMapperOps<MODEL, RESULT, String, SOURCE> 

	implements ISharedResultOps_String_Named<MODEL, RESULT, SOURCE> {

	ResultMapperOps_String(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, impl);
	}

	@Override
	public <T> ISharedResultOps_String_Named<MODEL, RESULT, SOURCE> concat(StringFunction<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <T> ISharedResultOps_String_Named<MODEL, RESULT, SOURCE> concat(String value) {
		throw new UnsupportedOperationException("TODO");
	}
}
