package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_MapToResult_Base<
			MODEL, 
			RESULT,
			NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>>


	extends Collector_Base<MODEL>
	
	implements IMappingCollector<MODEL, RESULT>, ISharedSelectSourceBuilder<MODEL, RESULT>

{
	
	Short_Collector_MapToResult_Base(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl<MODEL>(modelCompiler, result));
		
		final MappingCollector mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
	}

	@Override
	public final MappingCollector getMappingCollector() {
		return getQueryCollector().getMappings();
	}
}
