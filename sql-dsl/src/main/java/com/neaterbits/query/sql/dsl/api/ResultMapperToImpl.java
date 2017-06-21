package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

// TODO should be able to make this abstract
/* abstract */ class ResultMapperToImpl<
			MODEL,
			RESULT,
			R,
			SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>>


		extends CollectedItem
		implements ISharedResultMap_To<MODEL, RESULT, R, SOURCE> {

	
	private final Expression expression;
	
	private final IMappingCollector<MODEL, RESULT> impl;

	ResultMapperToImpl(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		
		if (expression == null) {
			throw new IllegalArgumentException("expression == null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}

		this.expression = expression;
		this.impl = impl;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final SOURCE to(BiConsumer<RESULT, R> setter) {
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		final MappingCollector mappingCollector = impl.getMappingCollector();

		if (expression != null) {
			mappingCollector.add(this, expression, setter);
		}
		else {
			throw new IllegalStateException("Neither getter nor supplier set");
		}
		
		return (SOURCE)impl;
	}
}
