package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

abstract class Classic_Collector_MapToResult_Base<
	MODEL,
	RESULT,
	
	NAMED_MAP_RESULT extends ISharedSelectSourceBuilder<MODEL, RESULT>,
	
	NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
	ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
	
	AFTER_GROUP_BY> 


	extends Classic_Collector_SelectSource<MODEL, RESULT, NAMED_WHERE_OR_JOIN, ALIAS_WHERE_OR_JOIN, AFTER_GROUP_BY> 

	implements IMappingCollector<MODEL, RESULT>,
		ISharedResultMap_Initial_Named<MODEL, RESULT, NAMED_MAP_RESULT>
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
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Boolean, NAMED_MAP_RESULT> map(IFunctionBoolean<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}
	
	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, NAMED_MAP_RESULT> map(IFunctionByte<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, NAMED_MAP_RESULT> map(IFunctionShort<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, NAMED_MAP_RESULT> map(IFunctionInteger<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, NAMED_MAP_RESULT> map(IFunctionLong<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, NAMED_MAP_RESULT> map(IFunctionBigInteger<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, NAMED_MAP_RESULT> map(IFunctionFloat<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, NAMED_MAP_RESULT> map(IFunctionDouble<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, NAMED_MAP_RESULT> map(IFunctionBigDecimal<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_String_Named<MODEL, RESULT, NAMED_MAP_RESULT> map(IFunctionString<T> getter) {
		return new ResultMapper_ExpressionList_String_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, NAMED_MAP_RESULT> map(IFunctionDate<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, NAMED_MAP_RESULT> map(IFunctionCalendar<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, NAMED_MAP_RESULT> map(IFunctionSQLDate<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, NAMED_MAP_RESULT> map(IFunctionSQLTime<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, NAMED_MAP_RESULT> map(IFunctionSQLTimestamp<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), this);
	}
}
