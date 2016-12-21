package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class AdhocQueryClass extends AdhocQueryBase<AdhocQueryClass>  implements IAdhocNumericTableResult<Object> {

	@SuppressWarnings("rawtypes")
	private Function aggregateGetter;

	
	AdhocQueryClass(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, ENumericType aggregateNumericType) {
		super(aggregateFunction, aggregateNumericType);

		if (aggregateGetter == null) {
			throw new IllegalArgumentException("aggregateGetter == null");
		}
		
		this.aggregateGetter = aggregateGetter;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Object getAggregateResultValue(AdhocQueryClass query, Object instance) {
		return aggregateGetter.apply(instance);
	}

	@Override
	public ExecuteQueryScratch createScratchArea(AdhocQueryClass query, QueryMetaModel queryMetaModel) {
		return this;
	}
}

