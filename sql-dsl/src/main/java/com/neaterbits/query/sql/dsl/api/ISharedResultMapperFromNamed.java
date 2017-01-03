package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedResultMapperFromNamed<MODEL, RESULT, SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> {

	<T, R> ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> map(Function<T, R> getter);

}
