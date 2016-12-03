package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * All select sources (from ... in query, compiled together)
 *  
 * @author nhl
 *
 */

final class CompiledSelectSources {

	private final List<CompiledSelectSource> sources;

	CompiledSelectSources(List<CompiledSelectSource> sources) {
		
		if (sources == null) {
			throw new IllegalArgumentException("sources == null");
		}

		this.sources = Collections.unmodifiableList(new ArrayList<>(sources));
	}

	List<CompiledSelectSource> getSources() {
		return sources;
	}
}
