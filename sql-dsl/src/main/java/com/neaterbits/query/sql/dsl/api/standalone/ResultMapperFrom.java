package com.neaterbits.query.sql.dsl.api.standalone;

import java.util.function.Function;

public interface ResultMapperFrom<RESULT, SOURCE extends SelectSourceBuilder<RESULT>> {

	<T, R> ResultMapperTo<RESULT, R, SOURCE> map(Function<T, R> getter);
	
}
