package com.neaterbits.gui.sql.dsl.api.standalone;

import java.util.function.Function;

public interface ResultMapperFrom<RESULT, SOURCE extends SelectSourceBuilder<RESULT>> {

	<T, R> ResultMapperTo<RESULT, R, SOURCE> map(Function<T, R> getter);
	
}
