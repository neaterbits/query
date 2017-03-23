package com.neaterbits.query.sql.dsl.api;

final class CompiledOrderByEntity extends CompiledOrderBy {

	CompiledOrderByEntity(CompiledFieldReference[] optionalGetters, ESortOrder[] sortOrders) {
		super(null, optionalGetters, sortOrders);
	}
}
