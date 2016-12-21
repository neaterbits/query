package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class ResultMapperToImpl<MODEL, RESULT, R, SOURCE extends SelectSourceBuilder<MODEL, RESULT>>
		extends QueryBuilderItem
		implements ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> {

	private final Function<?, ?> fromGetter;
	private final Supplier<?> fromSupplier;
	
	private final BaseMapToResultImpl<MODEL, RESULT> impl;

	ResultMapperToImpl(Function<?, ?> fromGetter, BaseMapToResultImpl<MODEL, RESULT> impl) {
		
		if (fromGetter == null) {
			throw new IllegalArgumentException("fromGetter == null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		
		this.fromGetter = fromGetter;
		this.fromSupplier = null;
		this.impl = impl;
	}

	ResultMapperToImpl(Supplier<?> fromSupplier, BaseMapToResultImpl<MODEL, RESULT> impl) {
		
		if (fromSupplier == null) {
			throw new IllegalArgumentException("fromGetter == null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		

		this.fromGetter = null;
		this.fromSupplier = fromSupplier;
		this.impl = impl;
	}

	@Override
	@SuppressWarnings("unchecked")
	public SOURCE to(BiConsumer<RESULT, R> setter) {
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		final MappingCollector mappingCollector = impl.getMappingCollector();

		if (fromGetter != null) {
			mappingCollector.add(this, fromGetter, setter);
		}
		else if (fromSupplier != null) {
			mappingCollector.add(this, fromSupplier, setter);
		}
		else {
			throw new IllegalStateException("Neither getter nor supplier set");
		}
		
		return (SOURCE)impl;
	}
}
