package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

final class CollectedMapping_Alias extends CollectedMapping {
	
	@Deprecated
	private final Supplier<?> getter;
	
	CollectedMapping_Alias(CollectedItem original, Expression expression, BiConsumer<?, ?> setter) {
		super(original, expression, setter);
		
		this.getter = null;
	}

	@Deprecated
	CollectedMapping_Alias(CollectedItem original, Supplier<?> getter, BiConsumer<?, ?> setter, CollectedFunctions functions) {
		super(original, new SupplierGetter(getter), setter, functions);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		this.getter = getter;
	}

	Supplier<?> getSupplierGetter() {
		return getter;
	}
}
