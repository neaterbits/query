package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class AdhocQueryNamedAggregate<MODEL> extends AdhocQueryNamedSingular<MODEL>
	implements 	IAdhocNumericNamedResult<MODEL, Object, Object>,
				IAdhocNumericInstanceResult<MODEL, Object>{

	AdhocQueryNamedAggregate(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction,
			ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateGetter, aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);
	}
}
