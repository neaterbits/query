package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

public interface ISharedResultMapperTo<MODEL, RESULT, R, SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> {

	SOURCE to(BiConsumer<RESULT, R> setter);
	
}
