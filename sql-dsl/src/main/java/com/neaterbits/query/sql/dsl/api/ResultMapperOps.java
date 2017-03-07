package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

abstract class ResultMapperOps<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>>

	extends ResultMapperToImpl<MODEL, RESULT, R, SOURCE> {

	ResultMapperOps(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl, CollectedFunctions collectedFunctions) {
		super(fromGetter, impl, collectedFunctions);
	}

	ResultMapperOps(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl) {
		super(fromGetter, impl);
	}

	ResultMapperOps(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl, CollectedFunctions collectedFunctions) {
		super(fromSupplier, impl, collectedFunctions);
	}

	ResultMapperOps(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl) {
		super(fromSupplier, impl);
	}
}
