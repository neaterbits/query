package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class FunctionGetter extends Getter {
	final Function<?, ?> getter;

	FunctionGetter(Function<?, ?> getter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}
}
