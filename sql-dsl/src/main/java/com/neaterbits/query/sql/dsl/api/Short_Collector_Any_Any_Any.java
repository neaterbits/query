package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Supplier;

abstract class Short_Collector_Any_Any_Any<
		MODEL,
		RESULT,
		
		MAPPED_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
		MAPPED_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		
		MAPPED_SOURCE_NAMED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		MAPPED_SOURCE_ALIAS extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		
		NAMED_AND_CLAUSES  extends ISharedLogical_And_Named_All <MODEL, RESULT, NAMED_AND_CLAUSES,  NAMED_NESTED_OR_CLAUSES>,
		NAMED_OR_CLAUSES  extends ISharedLogical_Or_Named_All <MODEL, RESULT, NAMED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,

		NAMED_NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
		NAMED_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,			

		NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION>,

		NAMED_AND_OR extends ISharedLogical_And_Or_Named_All<
			MODEL,
			RESULT,
			NAMED_AND_CLAUSES,
			NAMED_OR_CLAUSES,
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_OR_CLAUSES>,
		
		ALIAS_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
		ALIAS_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,

		ALIAS_NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
		ALIAS_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_NESTED_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,			

		ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, ALIAS_JOIN_CONDITION>,

		ALIAS_AND_OR extends ISharedLogical_And_Or_Alias<
			MODEL,
			RESULT,
			ALIAS_AND_CLAUSES,
			ALIAS_OR_CLAUSES,
			ALIAS_NESTED_AND_CLAUSES,
			ALIAS_NESTED_OR_CLAUSES>,
			
			
		AFTER_GROUP_BY
		>

	extends Short_Collector_WhereOrJoin_Base<
			MODEL,
			RESULT,

			MAPPED_NAMED_WHERE_OR_JOIN,
			MAPPED_ALIAS_WHERE_OR_JOIN,
			
			NAMED_AND_CLAUSES, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_JOIN_CONDITION, NAMED_AND_OR,
			ALIAS_AND_CLAUSES, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_JOIN_CONDITION, ALIAS_AND_OR,
			
			AFTER_GROUP_BY>

	implements ISharedMap_Initial_Named<MODEL, RESULT, MAPPED_SOURCE_NAMED>,
			   ISharedMap_Initial_Alias<MODEL, RESULT, MAPPED_SOURCE_ALIAS> {

		
	// abstract-method to call whenever collected-result is available at a later time
	// (do not know whether mapped or entity beforehand)
				   
	abstract IMappingCollector<MODEL, RESULT> getMapToResultNamed();

	abstract IMappingCollector<MODEL, RESULT> getMapToResultAlias();
				   
	private final SharedSelectSource selectSource;
	private final ModelCompiler<MODEL> modelCompiler;

	
	
	protected Short_Collector_Any_Any_Any(BaseQuery select, ModelCompiler<MODEL> modelCompiler, CollectedQueryResult result, SharedSelectSource selectSource) {
		super(new QueryCollectorImpl<MODEL>(select, modelCompiler, result));
		
		if (selectSource == null && !(result instanceof CollectedQueryResult_Aggregate)) {
			throw new IllegalArgumentException("selectSource == null");
		}
		
		if (modelCompiler == null) {
			throw new IllegalArgumentException("modelCompiler == null");
		}
		
		this.selectSource = selectSource;
		this.modelCompiler = modelCompiler;
	}

	// TODO: for aggregates, perhaps separate baseclass?
	@Deprecated
	protected Short_Collector_Any_Any_Any(BaseQuery select, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl<MODEL>(select, modelCompiler, null));
		
		if (modelCompiler == null) {
			throw new IllegalArgumentException("modelCompiler == null");
		}
		
		this.selectSource = null; // aggregate queries 
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
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Boolean, MAPPED_SOURCE_NAMED> map(IFunctionBoolean<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}
	
	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, MAPPED_SOURCE_NAMED> map(IFunctionByte<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}
	
	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, MAPPED_SOURCE_NAMED> map(IFunctionShort<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, MAPPED_SOURCE_NAMED> map(IFunctionInteger<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, MAPPED_SOURCE_NAMED> map(IFunctionLong<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, MAPPED_SOURCE_NAMED> map(IFunctionBigInteger<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, MAPPED_SOURCE_NAMED> map(IFunctionBigDecimal<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, MAPPED_SOURCE_NAMED> map(IFunctionFloat<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, MAPPED_SOURCE_NAMED> map(IFunctionDouble<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}
	
	@Override
	public final <T> ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, MAPPED_SOURCE_NAMED> map(IFunctionString<T> getter) {
		return new ResultMapper_ExpressionList_String_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, MAPPED_SOURCE_NAMED> map(IFunctionDate<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, MAPPED_SOURCE_NAMED> map(IFunctionCalendar<T> getter) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, MAPPED_SOURCE_NAMED> map(IFunctionSQLDate<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}
	
	@Override
	public final <T> ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, MAPPED_SOURCE_NAMED> map(IFunctionSQLTime<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <T> ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, MAPPED_SOURCE_NAMED> map(IFunctionSQLTimestamp<T> getter) {
		return new ResultMapper_ExpressionList_SQLTimeType_Named<>(new FieldExpression(getter), getMapToResultNamed());
	}

	@Override
	public final <R> ISharedMap_To<MODEL, RESULT, R, MAPPED_SOURCE_ALIAS> map(Supplier<R> getter) {
		return new ResultMapperToImpl<>(new FieldExpression(getter), getMapToResultAlias());
	}
}
