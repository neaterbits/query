package com.neaterbits.query.sql.dsl.impl.standalone;

import com.neaterbits.query.sql.dsl.api.standalone.Alias;

final class AliasImpl<TABLE> implements Alias<TABLE> {

	private final Class<TABLE> aliasType;

	AliasImpl(Class<TABLE> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}

		this.aliasType = aliasType;
	}

	Class<TABLE> getAliasType() {
		return aliasType;
	}
}

