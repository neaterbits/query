package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ResultMapperFromTable<MODEL, RESULT, SOURCE extends SelectSourceBuilder<MODEL, RESULT>> {

	<T, R> ResultMapperTo<MODEL, RESULT, R, SOURCE> mapF(Function<T, R> getter);

}
