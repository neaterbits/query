package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class AdhocQueryNamed_Aggregate<MODEL> extends AdhocQueryNamed_Singular<MODEL>
	implements 	IAdhocNumericNamedResult<MODEL, Object, Object>,
				IAdhocNumericInstanceResult<MODEL, Object>{

	AdhocQueryNamed_Aggregate(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction,
			ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateGetter, aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);
	}
}
