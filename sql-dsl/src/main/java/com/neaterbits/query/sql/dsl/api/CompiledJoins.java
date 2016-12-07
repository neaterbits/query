package com.neaterbits.query.sql.dsl.api;

import java.util.List;

abstract class CompiledJoins {

	private final List<CompiledJoin> joins;

	CompiledJoins(List<CompiledJoin> joins) {
		
		if (joins == null) {
			throw new IllegalArgumentException("joins == null");
		}
		
		if (joins.isEmpty()) {
			throw new IllegalArgumentException("no joins");
		}
		
		this.joins = joins;
	}
}
