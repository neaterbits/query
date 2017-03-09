package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

// TODO should be able to make this abstract
/* abstract */ class ResultMapperToImpl<
			MODEL,
			RESULT,
			R,
			SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>>


		extends CollectedItem
		implements ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> {

	
	private final Expression expression;
	
	@Deprecated
	private final Function<?, ?> fromGetter;
	@Deprecated
	private final Supplier<?> fromSupplier;
	
	private final IMappingCollector<MODEL, RESULT> impl;
	@Deprecated
	private final CollectedFunctions collectedFunctions;

	ResultMapperToImpl(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		
		if (expression == null) {
			throw new IllegalArgumentException("expression == null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}

		this.expression = expression;
		this.impl = impl;
		
		this.collectedFunctions = null;
		this.fromGetter = null;
		this.fromSupplier = null;
	}

	@Deprecated
	ResultMapperToImpl(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl) {
		this(fromGetter, impl, null);
	}
	
	@Deprecated
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

		this.expression = null;
	}

	@Deprecated
	ResultMapperToImpl(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl) {
		this(fromSupplier, impl, null);
	}

	@Deprecated
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

		this.expression = null;
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
		else if (expression != null) {
			mappingCollector.add(this, expression, setter);
		}
		else {
			throw new IllegalStateException("Neither getter nor supplier set");
		}
		
		return (SOURCE)impl;
	}
}
