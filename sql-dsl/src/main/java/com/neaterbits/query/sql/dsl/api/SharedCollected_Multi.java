package com.neaterbits.query.sql.dsl.api;

final class SharedCollected_Multi<MODEL, RESULT> 
	extends SharedCollected_Base<MODEL> 
	implements MultiCompiled<RESULT> {

	SharedCollected_Multi(Collector_Query<MODEL> collected) {
		super(collected);
	}

	@Override
	public MultiPrepared<RESULT> prepare(DataConfig dataConfig) {
		// Have to both compile and prepare
		return SharedCompiled_Multi.prepare(dataConfig, compile(dataConfig, getCollected()));
	}
}
