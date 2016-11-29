package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ResultMapperFromAlias<MODEL, RESULT, SOURCE extends SelectSourceBuilder<MODEL, RESULT>> {

	<T, R> ResultMapperTo<MODEL, RESULT, R, SOURCE> map(Supplier<R> getter);
	
}
