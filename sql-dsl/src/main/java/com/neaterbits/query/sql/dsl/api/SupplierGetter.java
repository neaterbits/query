package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

final class SupplierGetter extends Getter {

	private final Supplier<?> getter;

	SupplierGetter(Supplier<?> getter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		this.getter = getter;
	}
}
