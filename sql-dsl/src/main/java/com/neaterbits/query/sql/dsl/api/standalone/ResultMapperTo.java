package com.neaterbits.query.sql.dsl.api.standalone;

import java.util.function.BiConsumer;

public interface ResultMapperTo<RESULT, R, SOURCE extends SelectSourceBuilder<RESULT>> {

	SOURCE to(BiConsumer<RESULT, R> setter);
	
}
