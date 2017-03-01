package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

abstract class Short_Collector_Result<
		MODEL,
		RESULT,
		
		MAPPED_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
		MAPPED_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		
		MAPPED_SOURCE_NAMED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		MAPPED_SOURCE_ALIAS extends ISharedSelectSourceBuilder<MODEL, RESULT>>

	extends Collector_Base<MODEL>

	implements ISharedResultMapper_Named<MODEL, RESULT, MAPPED_SOURCE_NAMED>,
			   ISharedResultMapper_Alias<MODEL, RESULT, MAPPED_SOURCE_ALIAS> {

		
	abstract Short_Collector_MapToResult_Base<MODEL, RESULT, MAPPED_NAMED_WHERE_OR_JOIN, MAPPED_ALIAS_WHERE_OR_JOIN>
			createMapToResult();
		
	private final SharedSelectSource selectSource;
	private final ModelCompiler<MODEL> modelCompiler;

	protected Short_Collector_Result(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl<MODEL>(modelCompiler, null));
		
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
