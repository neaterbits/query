package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class AdhocQueryClassAggregate<MODEL> extends AdhocQueryClassSingular<MODEL>
	implements 	IAdhocNumericTableResult<MODEL, Object, Object>,
				IAdhocNumericInstanceResult<MODEL, Object>{

	AdhocQueryClassAggregate(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction,
			ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateGetter, aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);
	}
}
