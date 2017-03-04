package com.neaterbits.query.sql.dsl.api;

abstract class Classic_Collector_MapToResult_Base<
	MODEL,
	RESULT,
	NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
	ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>> 


	extends Classic_Collector_SelectSource<MODEL, RESULT, NAMED_WHERE_OR_JOIN, ALIAS_WHERE_OR_JOIN> 

	implements IMappingCollector<MODEL, RESULT>
{

	Classic_Collector_MapToResult_Base(ClassicSelect select, CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);

		final MappingCollector mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
	}

	@Override
	public final MappingCollector getMappingCollector() {
		return getQueryCollector().getMappings();
	}
}
