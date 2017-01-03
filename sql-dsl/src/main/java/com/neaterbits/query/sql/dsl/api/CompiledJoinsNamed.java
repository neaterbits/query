package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledJoinsNamed extends CompiledJoins {
	CompiledJoinsNamed(List<CompiledJoin> joins) {
		super(joins);
	}
}
