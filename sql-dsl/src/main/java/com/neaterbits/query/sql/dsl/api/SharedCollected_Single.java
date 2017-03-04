package com.neaterbits.query.sql.dsl.api;

final class SharedCollected_Single<MODEL, RESULT>

	extends SharedCollected_Base<MODEL>
	implements SingleBuilt<RESULT> {
	
	

	public SharedCollected_Single(Collector_Query<MODEL> collected) {
		super(collected);
		
		if (collected.getQueryMetaModel() != null) {
			throw new IllegalStateException("should have been able to compile directly");
		}
	}
	
	@Override
	public SinglePrepared<RESULT> prepare(DataConfig dataConfig) {
		// Have to both compile and prepare
		return SharedCompiled_Single.prepare(dataConfig, compile(dataConfig, getCollected()));
	}
}
