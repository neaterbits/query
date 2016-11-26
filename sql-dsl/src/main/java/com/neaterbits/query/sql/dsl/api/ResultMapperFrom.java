package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ResultMapperFrom<MODEL, RESULT, SOURCE extends SelectSourceBuilder<MODEL, RESULT>> {

	<T, R> ResultMapperTo<MODEL, RESULT, R, SOURCE> map(Function<T, R> getter);
	
}
