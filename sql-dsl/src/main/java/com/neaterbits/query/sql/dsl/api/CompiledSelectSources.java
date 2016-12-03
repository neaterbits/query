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

final class CompiledSelectSources extends CompiledQueryElement<SelectSourceImpl> {

	private final List<CompiledSelectSource> sources;

	CompiledSelectSources(SelectSourceImpl original, List<CompiledSelectSource> sources) {
		
		super(original);

		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}

		if (sources == null) {
			throw new IllegalArgumentException("sources == null");
		}

		this.sources = Collections.unmodifiableList(new ArrayList<>(sources));
	}

	List<CompiledSelectSource> getSources() {
		return sources;
	}
}
