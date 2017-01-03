package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledJoinsAlias extends CompiledJoins {

	CompiledJoinsAlias(List<CompiledJoin> joins) {
		super(joins);
	}

}
