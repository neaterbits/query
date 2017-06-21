package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Supplier;

abstract class Classic_Collector_Result<
			MODEL, 
			RESULT,
			
			NAMED_MAP_RESULT extends ISharedSelectSourceBuilder<MODEL, RESULT>,
			
			ENTITY_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			ENTITY_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
			
			MAPPED_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			MAPPED_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
			
			MAPPED_SOURCE_NAMED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
			MAPPED_SOURCE_ALIAS extends ISharedSelectSourceBuilder<MODEL, RESULT>,
			
			AFTER_GROUP_BY
			
	>


	// all classic-queries can have 'from' at beginning, so collect that
	extends Classic_Collector_SelectSource<MODEL, RESULT, ENTITY_NAMED_WHERE_OR_JOIN, ENTITY_ALIAS_WHERE_OR_JOIN, AFTER_GROUP_BY> 

	implements ISharedResultMap_Initial_Named<MODEL, RESULT, MAPPED_SOURCE_NAMED>,
			   ISharedResultMap_Initial_Alias<MODEL, RESULT, MAPPED_SOURCE_ALIAS> {
	
	
	abstract Classic_Collector_MapToResult_Base<MODEL, RESULT, NAMED_MAP_RESULT, MAPPED_NAMED_WHERE_OR_JOIN, MAPPED_ALIAS_WHERE_OR_JOIN, AFTER_GROUP_BY> createMapToResult();

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
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Boolean, MAPPED_SOURCE_NAMED> map(IFunctionBoolean<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, MAPPED_SOURCE_NAMED> map(IFunctionByte<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, MAPPED_SOURCE_NAMED> map(IFunctionShort<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, MAPPED_SOURCE_NAMED> map(IFunctionInteger<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, MAPPED_SOURCE_NAMED> map(IFunctionLong<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, MAPPED_SOURCE_NAMED> map(IFunctionBigInteger<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, MAPPED_SOURCE_NAMED> map(IFunctionFloat<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}
	
	@Override
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, MAPPED_SOURCE_NAMED> map(IFunctionDouble<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}
	
	@Override
	public <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, MAPPED_SOURCE_NAMED> map(IFunctionBigDecimal<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public <T> ISharedResultMap_OpsAndTo_String_Named<MODEL, RESULT, MAPPED_SOURCE_NAMED> map(IFunctionString<T> getter) {
		return new ResultMapper_ExpressionList_String_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, MAPPED_SOURCE_NAMED> map(IFunctionDate<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, MAPPED_SOURCE_NAMED> map(IFunctionCalendar<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, MAPPED_SOURCE_NAMED> map(IFunctionSQLDate<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, MAPPED_SOURCE_NAMED> map(IFunctionSQLTime<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, MAPPED_SOURCE_NAMED> map(IFunctionSQLTimestamp<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), createMapToResult());
	}

	@Override
	public final <R> ISharedResultMap_To<MODEL, RESULT, R, MAPPED_SOURCE_ALIAS> map(Supplier<R> getter) {
		return new ResultMapperToImpl<>(new FieldExpression(getter), createMapToResult());
	}
}
