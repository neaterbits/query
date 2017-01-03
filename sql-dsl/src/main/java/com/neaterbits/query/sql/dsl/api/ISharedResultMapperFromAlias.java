package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedResultMapperFromAlias<MODEL, RESULT, SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> {

	<T, R> ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> map(Supplier<R> getter);
	
}
