package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collection;

final class ResultCollection_List extends ArrayList<Object> implements ResultCollection {

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
