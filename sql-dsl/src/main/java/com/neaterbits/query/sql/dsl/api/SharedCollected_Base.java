package com.neaterbits.query.sql.dsl.api;

/**
 * Base class for queries that cannot be compiled until
 * datasource is given eg. 'short' stype queries 
 
 * @author nhl
 *
 */

abstract class SharedCollected_Base<MODEL> {

	private final Collector_Query<MODEL> collected;

	SharedCollected_Base(Collector_Query<MODEL> collected) {
		
		if (collected == null) {
			throw new IllegalArgumentException("collected == null");
		}
		
		this.collected = collected;
	}

	static <M>CompiledQuery compile(DataConfig dataConfig, Collector_Query<M> collected) {
		@SuppressWarnings("rawtypes")
		final DataConfigBase baseConfig = (DataConfigBase)dataConfig;
		
		final CompiledQuery compiled;
		
		try {
			compiled = CompiledQuery.compile(collected, baseConfig.getQueryMetaModel());
		} catch (CompileException ex) {
			throw new IllegalStateException("Failed to compiled", ex);
		}

		return compiled;
	}
	
	final Collector_Query<MODEL> getCollected() {
		return collected;
	}
}
