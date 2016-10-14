package com.neaterbits.query.sql.dsl.impl.standalone;

import java.util.function.BiConsumer;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.standalone.ResultMapperTo;
import com.neaterbits.query.sql.dsl.api.standalone.SelectSourceBuilder;

final class ResultMapperToImpl<RESULT, R, SOURCE extends SelectSourceBuilder<RESULT>>
		implements ResultMapperTo<RESULT, R, SOURCE> {

	private final Function<?, ?> fromGetter;
	private final SingleResultToResultImpl<RESULT> impl;

	ResultMapperToImpl(Function<?, ?> fromGetter, SingleResultToResultImpl<RESULT> impl) {
		
		if (fromGetter != null) {
			throw new IllegalArgumentException("fromGetter != null");
		}

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		
		this.fromGetter = fromGetter;
		this.impl = impl;
	}

	@Override
	public SOURCE to(BiConsumer<RESULT, R> setter) {

		//impl.map(getter);
		
		
		return null;
	}
}
