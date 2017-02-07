package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.HashSet;

final class ResultCollection_Set extends HashSet<Object> implements ResultCollection {

	private static final long serialVersionUID = 1L;

	@Override
	public void addResult(Object o) {
		
		if (o == null) {
			throw new IllegalArgumentException("o == null");
		}
		super.add(o);
	}

	@Override
	public Collection<Object> asCollection() {
		return this;
	}
}
