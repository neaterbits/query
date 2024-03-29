package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

public interface ISharedMap_To<MODEL, RESULT, R, SOURCE extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedFunction_Next<MODEL, RESULT, SOURCE> {

	SOURCE to(BiConsumer<RESULT, R> setter);
	
}
