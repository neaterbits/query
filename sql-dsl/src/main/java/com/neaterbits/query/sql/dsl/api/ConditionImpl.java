package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

abstract class ConditionImpl {

	private final Function<?, ?> getter;
	
	ConditionImpl(Function<?, ?> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		this.getter = getter;
	}


	final Function<?, ?> getGetter() {
		return getter;
	}

	abstract <T, R> R visit(ConditionVisitor<T, R> visitor, T param);

}
