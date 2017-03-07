package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class ResultMapperOps_String<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>>

	extends ResultMapperOps<MODEL, RESULT, String, SOURCE> 

	implements ISharedResultOps_String_Named<MODEL, RESULT, SOURCE> {

	ResultMapperOps_String(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl, CollectedFunctions collectedFunctions) {
		super(fromGetter, impl, collectedFunctions);
	}

	ResultMapperOps_String(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl) {
		super(fromGetter, impl);
	}

	ResultMapperOps_String(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl, CollectedFunctions collectedFunctions) {
		super(fromSupplier, impl, collectedFunctions);
	}

	ResultMapperOps_String(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl) {
		super(fromSupplier, impl);
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
