package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.function.Function;

public interface CollectionFunction<LEFT, RIGHT> extends Function<LEFT, Collection<RIGHT>> {
	
}
