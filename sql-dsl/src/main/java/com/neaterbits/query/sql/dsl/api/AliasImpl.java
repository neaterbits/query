package com.neaterbits.query.sql.dsl.api;


final class AliasImpl<T> implements Alias<T> {

	private final Class<T> aliasType;

	AliasImpl(Class<T> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}

		this.aliasType = aliasType;
	}

	Class<T> getAliasType() {
		return aliasType;
	}
}

