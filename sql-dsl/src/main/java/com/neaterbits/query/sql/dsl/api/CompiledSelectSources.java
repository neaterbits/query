package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All select sources (from ... in query, compiled together)
 *  
 * @author nhl
 *
 */

abstract class CompiledSelectSources<T extends CompiledSelectSource>
	extends CompiledQueryElement<CollectedSelectSource>
	implements TypeMap {

	private final List<T> sources;
	
	// Cache types
	private final List<Class<?>> types;

	abstract TypeMapSource getNamedSource(Class<?> type);
	abstract TypeMapSource getAliasesSource(IAlias alias);
	
	abstract int getSourceIdx(SharedSelectSource source);
	
	final int getCount() {
		return sources.size();
	}
	
	CompiledSelectSources(CollectedSelectSource original, List<T> sources) {
		super(original);

		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}

		if (sources == null) {
			throw new IllegalArgumentException("sources == null");
		}

		this.sources = Collections.unmodifiableList(new ArrayList<>(sources));
		this.types = Arrays.asList(original.getTypes());
	}
	
	CompiledSelectSources(List<T> sources) {
		super(null);

		if (sources == null) {
			throw new IllegalArgumentException("sources == null");
		}

		this.sources = Collections.unmodifiableList(new ArrayList<>(sources));
		this.types = sources.stream().map(s -> s.getType()).collect(Collectors.toList());
	}
	
	
	Iterable<Class<?>> getTypes() {
		return types;
	}

	final List<T> getSources() {
		return sources;
	}
}

