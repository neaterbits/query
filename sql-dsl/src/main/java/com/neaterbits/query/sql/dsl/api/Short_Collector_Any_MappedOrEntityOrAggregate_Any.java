package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Supplier;

abstract class Short_Collector_Any_MappedOrEntityOrAggregate_Any<
			MODEL, 
			RESULT,
			
			NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
			
			NAMED_MAPPED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
			ALIAS_MAPPED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
			
			
			NAMED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
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
			
			NAMED_WHERE_OR_JOIN,
			ALIAS_WHERE_OR_JOIN,
			
			NAMED_AND_CLAUSES, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_JOIN_CONDITION, NAMED_AND_OR,
			
			ALIAS_AND_CLAUSES, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_JOIN_CONDITION, ALIAS_AND_OR,

			AFTER_GROUP_BY
			>
	
	implements IMappingCollector<MODEL, RESULT>, ISharedSelectSourceBuilder<MODEL, RESULT>,
	
	
	// cannot implement because at this point, we've decided named or alias,
	// we only implement both in base class for reuse

    // IShortResult_Mapped_Single_All<MODEL, RESULT>


		ISharedResultMapper_Named<MODEL, RESULT, NAMED_MAPPED>,
		ISharedResultMapper_Alias<MODEL, RESULT, ALIAS_MAPPED>
		
	{
	
		
	@Deprecated // should not be necessary here as is in result
	private final EQueryResultGathering gathering;

	Short_Collector_Any_MappedOrEntityOrAggregate_Any(Collector_Query<MODEL> queryCollector) {
		super(queryCollector, new Collector_Clause(EConditionsClause.WHERE, ConditionsType.SINGLE));
		
		// do not set mapping collector
		
		this.gathering = queryCollector.getResult().getGathering();
	}
	
		
	Short_Collector_Any_MappedOrEntityOrAggregate_Any(Collector_Query<MODEL> queryCollector, CollectedQueryResult_Mapped result) {
		super(new QueryCollectorImpl<MODEL>(queryCollector, result), new Collector_Clause(EConditionsClause.WHERE, ConditionsType.SINGLE));

		if (getQueryCollector().getMappings() == null) {
			getQueryCollector().setMappings(new MappingCollector());
		}

		// Collect mappings, should ever only create one of these
		
		this.gathering = EQueryResultGathering.MAPPED;
	}

	Short_Collector_Any_MappedOrEntityOrAggregate_Any(Collector_Query<MODEL> queryCollector, CollectedQueryResult_Entity result) {
		super(new QueryCollectorImpl<MODEL>(queryCollector, result), new Collector_Clause(EConditionsClause.WHERE, ConditionsType.SINGLE));
		
		// do not set mapping collector
		
		this.gathering = EQueryResultGathering.ENTITY;
	}

	Short_Collector_Any_MappedOrEntityOrAggregate_Any(Collector_Query<MODEL> queryCollector, CollectedQueryResult_Aggregate result) {
		super(new QueryCollectorImpl<MODEL>(queryCollector, result), new Collector_Clause(EConditionsClause.WHERE, ConditionsType.SINGLE));
		
		// do not set mapping collector
		
		this.gathering = EQueryResultGathering.AGGREGATE;
	}
	
	Short_Collector_Any_MappedOrEntityOrAggregate_Any(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
		
		this.gathering = last.getQueryCollector().getResult().getGathering();
	}
	
	@Override
	public final <T> ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Short, NAMED_MAPPED> map(IFunctionShort<T> getter) {
		return new ResultMapper_ExpressionList_Numeric_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer, NAMED_MAPPED> map(IFunctionInteger<T> getter) {
		return new ResultMapper_ExpressionList_Numeric_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, NAMED_MAPPED> map(IFunctionLong<T> getter) {
		return new ResultMapper_ExpressionList_Numeric_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, BigDecimal, NAMED_MAPPED> map(IFunctionBigDecimal<T> getter) {
		return new ResultMapper_ExpressionList_Numeric_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <T> ISharedResultOps_String_Named<MODEL, RESULT, NAMED_MAPPED> map(StringFunction<T> getter) {
		return new ResultMapper_ExpressionList_String_Named<>(new FieldExpression(getter), this);
	}

	@Override
	public final <R> ISharedResultMapperTo<MODEL, RESULT, R, ALIAS_MAPPED> map(Supplier<R> getter) {
		return new ResultMapperToImpl<>(new FieldExpression(getter), this);
	}
	

	@Override
	public final MappingCollector getMappingCollector() {
		
		if (gathering != EQueryResultGathering.MAPPED) {
			throw new IllegalStateException("Only to be called for mapped queries");
		}

		return getQueryCollector().getMappings();
	}
}
