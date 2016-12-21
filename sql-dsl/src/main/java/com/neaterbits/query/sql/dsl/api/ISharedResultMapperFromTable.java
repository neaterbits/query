package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedResultMapperFromTable<MODEL, RESULT, SOURCE extends SelectSourceBuilder<MODEL, RESULT>> {

	<T, R> ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> map(Function<T, R> getter);

}
