package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

abstract class Classic_Collector_MapToResult_Base<
	MODEL,
	RESULT,
	
	NAMED_MAP_RESULT extends ISharedSelectSourceBuilder<MODEL, RESULT>,
	
	NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
	ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>> 


	extends Classic_Collector_SelectSource<MODEL, RESULT, NAMED_WHERE_OR_JOIN, ALIAS_WHERE_OR_JOIN> 

	implements IMappingCollector<MODEL, RESULT>,
		ISharedResultMapper_Named<MODEL, RESULT, NAMED_MAP_RESULT>
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

	@Override
	public final <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Short, NAMED_MAP_RESULT> map(IFunctionShort<T> getter) {
		return new ResultMapperOps_Numeric<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Integer, NAMED_MAP_RESULT> map(IFunctionInteger<T> getter) {
		return new ResultMapperOps_Numeric<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, NAMED_MAP_RESULT> map(IFunctionLong<T> getter) {
		return new ResultMapperOps_Numeric<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, BigDecimal, NAMED_MAP_RESULT> map(IFunctionBigDecimal<T> getter) {
		return new ResultMapperOps_Numeric<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultOps_String_Named<MODEL, RESULT, NAMED_MAP_RESULT> map(StringFunction<T> getter) {
		return new ResultMapperOps_String<>(new FieldExpression(getter), this);
	}
}
