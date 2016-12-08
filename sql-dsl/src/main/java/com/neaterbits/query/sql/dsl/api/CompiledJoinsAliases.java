package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledJoinsAliases extends CompiledJoins {

	CompiledJoinsAliases(List<CompiledJoin> joins) {
		super(joins);
	}

}
