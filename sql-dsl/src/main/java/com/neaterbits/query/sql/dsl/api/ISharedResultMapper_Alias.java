package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedResultMapper_Alias<MODEL, RESULT, SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> {

	<R> ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> map(Supplier<R> getter);
	
}
