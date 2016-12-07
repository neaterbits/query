package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledJoinsClasses extends CompiledJoins {
	CompiledJoinsClasses(List<CompiledJoin> joins) {
		super(joins);
	}
}
