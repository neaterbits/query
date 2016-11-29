package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class CompiledMappings {

	private final List<CompiledMapping> mappings;

	CompiledMappings(Collection<CompiledMapping> mappings) {
		this.mappings = Collections.unmodifiableList(new ArrayList<>(mappings));
	}

	public List<CompiledMapping> getMappings() {
		return mappings;
	}
}
