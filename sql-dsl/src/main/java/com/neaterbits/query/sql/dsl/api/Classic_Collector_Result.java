package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

abstract class Classic_Collector_Result<
			MODEL, 
			RESULT,
			ENTITY_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			ENTITY_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
			
			MAPPED_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			MAPPED_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
			
			MAPPED_SOURCE_NAMED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
			MAPPED_SOURCE_ALIAS extends ISharedSelectSourceBuilder<MODEL, RESULT>
			
	>


	// all classic-queries can have 'from' at beginning, so collect that
	extends Classic_Collector_SelectSource<MODEL, RESULT, ENTITY_NAMED_WHERE_OR_JOIN, ENTITY_ALIAS_WHERE_OR_JOIN> 

	implements ISharedResultMapper_Named<MODEL, RESULT, MAPPED_SOURCE_NAMED>,
			   ISharedResultMapper_Alias<MODEL, RESULT, MAPPED_SOURCE_ALIAS> {
	
	
	abstract Classic_Collector_MapToResult_Base<MODEL, RESULT, MAPPED_NAMED_WHERE_OR_JOIN, MAPPED_ALIAS_WHERE_OR_JOIN> createMapToResult();

	private final SharedSelectSource selectSource;
	private final ModelCompiler<MODEL> modelCompiler;
	
	Classic_Collector_Result(ClassicSelect classic, SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(classic, null, modelCompiler);
		
		if (selectSource == null) {
			throw new IllegalArgumentException("selectSource == null");
		}
		
		if (modelCompiler == null) {
			throw new IllegalArgumentException("modelCompiler == null");
		}
		
		this.selectSource = selectSource;
		this.modelCompiler = modelCompiler;
	}

	final Class<?> getResultType() {
		return selectSource.getType();
	}

	final SharedSelectSource getSelectSource() {
		return selectSource;
	}

	final ModelCompiler<MODEL> getModelCompiler() {
		return modelCompiler;
	}

	@Override
	public final <T, R> ISharedResultMapperTo<MODEL, RESULT, R, MAPPED_SOURCE_NAMED> map(Function<T, R> getter) {
		return new ResultMapperToImpl<>(getter, createMapToResult());
	}


	@Override
	public final <T, R> ISharedResultMapperTo<MODEL, RESULT, R, MAPPED_SOURCE_ALIAS> map(Supplier<R> getter) {
		return new ResultMapperToImpl<>(getter, createMapToResult());
	}
}
