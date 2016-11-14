package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;

final class ResultMapperToImpl<RESULT, R, SOURCE extends SelectSourceBuilder<RESULT>>
		implements ResultMapperTo<RESULT, R, SOURCE> {

	private final Function<?, ?> fromGetter;
	private final BaseMapToResultImpl<RESULT> impl;

	ResultMapperToImpl(Function<?, ?> fromGetter, BaseMapToResultImpl<RESULT> impl) {
		
		if (fromGetter == null) {
			throw new IllegalArgumentException("fromGetter == null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		
		this.fromGetter = fromGetter;
		this.impl = impl;
	}

	@Override
	@SuppressWarnings("unchecked")
	public SOURCE to(BiConsumer<RESULT, R> setter) {
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		impl.getMappingCollector().add(fromGetter, setter);
		
		return (SOURCE)impl;
	}
}
