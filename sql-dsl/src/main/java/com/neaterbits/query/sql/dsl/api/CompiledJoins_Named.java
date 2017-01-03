package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledJoins_Named extends CompiledJoins {
	CompiledJoins_Named(List<CompiledJoin> joins) {
		super(joins);
	}
}
