package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

class ResultMapperToImpl<
			MODEL,
			RESULT,
			R,
			SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>>


		extends CollectedItem
		implements ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> {

	private final Function<?, ?> fromGetter;
	private final Supplier<?> fromSupplier;
	
	private final IMappingCollector<MODEL, RESULT> impl;
	private final CollectedFunctions collectedFunctions;

	ResultMapperToImpl(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl) {
		this(fromGetter, impl, null);
	}
	
	ResultMapperToImpl(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl, CollectedFunctions collectedFunctions) {
		
		if (fromGetter == null) {
			throw new IllegalArgumentException("fromGetter == null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.fromGetter = fromGetter;
		this.fromSupplier = null;
		this.impl = impl;
		this.collectedFunctions = collectedFunctions;
	}

	ResultMapperToImpl(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl) {
		this(fromSupplier, impl, null);
	}

	ResultMapperToImpl(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl, CollectedFunctions collectedFunctions) {
		
		if (fromSupplier == null) {
			throw new IllegalArgumentException("fromGetter == null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}

		this.fromGetter = null;
		this.fromSupplier = fromSupplier;
		this.impl = impl;
		this.collectedFunctions = collectedFunctions;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final SOURCE to(BiConsumer<RESULT, R> setter) {
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		final MappingCollector mappingCollector = impl.getMappingCollector();

		if (fromGetter != null) {
			mappingCollector.add(this, fromGetter, setter, collectedFunctions);
		}
		else if (fromSupplier != null) {
			mappingCollector.add(this, fromSupplier, setter, collectedFunctions);
		}
		else {
			throw new IllegalStateException("Neither getter nor supplier set");
		}
		
		return (SOURCE)impl;
	}
}
