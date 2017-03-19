package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Supplier;

final class Short_Collector_Single_Any_Any<MODEL, RESULT>
	extends Short_Collector_Any_Any_Any<
			MODEL,
			RESULT,
			
			IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, RESULT>,
			IShortLogical_WhereOrJoin_SingleResult_Entity_Alias<MODEL, RESULT>,
			/*
			ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
			*/
						
			IShortResult_Mapped_Single_Named<MODEL, RESULT>,
			IShortResult_Mapped_Single_Alias<MODEL, RESULT>,

			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLJoin_Condition_SingleResult_Named<MODEL, RESULT, Object, Object>,
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>,

			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			IShortJoin_Condition_SingleResult_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,

			Void
			>

	implements
			ISQLLogical_Where_SingleResult_Named_Base<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			IShortLogical_WhereOrJoin_SingleResult_Entity_Named_Initial<MODEL, RESULT>,
			IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, RESULT>,
			
			IShortResult_Single<MODEL, RESULT>,
			IMappingCollector<MODEL, RESULT> {
	
	private final BaseQuery select;
	

	Short_Collector_Single_Any_Any(BaseQuery select, SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(select, selectSource, modelCompiler);
		
		this.select = select;
	}

	@Override
	CollectedQueryResult getResultWhenNotPresent() {
		// When string to build() so return multi-entity
		return new CollectedQueryResult_Entity_Single(getSelectSource());
	}

	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultNamed() {
		
		final CollectedQueryResult_Mapped_Single collectedQueryResult = new CollectedQueryResult_Mapped_Single(getResultType());
		
		return new Short_Collector_Single_Mapped_Named<MODEL, RESULT>(select, collectedQueryResult, getQueryCollector());
	}


	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultAlias() {
		final CollectedQueryResult_Mapped_Single collectedQueryResult = new CollectedQueryResult_Mapped_Single(getResultType());

		return new Short_Collector_Single_Mapped_Alias<MODEL, RESULT>(select, collectedQueryResult, getQueryCollector());
	}
	

	
	
	
	// ******************* !!! IMPORTANT must switch to one that implements AndOr interface !!! *******************


	@Override
	public <T> IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, T> joinRoot(Class<T> type) {
		
		// Just for type-checking and readability, telling where join starts from, if not from root 
		
		return (IShortLogical_WhereOrJoin_SingleResult_Entity_Named)this;
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>> where() {
		throw new UnsupportedOperationException("TODO");
	}
	

	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereNamed() {
		
		// TODO: rename *MapToResult* here
		final CollectedQueryResult_Entity_Single collectedQueryResult = new CollectedQueryResult_Entity_Single(getSelectSource());

		return new Short_Collector_Single_Mapped_Named<>(select, collectedQueryResult, getQueryCollector());
	}


	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereAlias() {
		// TODO: rename *MapToResult* here
		final CollectedQueryResult_Entity_Single collectedQueryResult = new CollectedQueryResult_Entity_Single(getSelectSource());

		return new Short_Collector_Single_Mapped_Alias<>(select, collectedQueryResult, getQueryCollector());
	}
	
	@Override
	final Collector_Or_Named<
			MODEL, 
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
		createNamedOrCollector() {
		
		return new SQL_Collector_Or_NonProcessResult_Named<>(this);
	}


	@Override
	final Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<
			MODEL,
			RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
		createNamedAndCollector() {

		return new SQL_Collector_And_NonProcessResult_Named<>(this);
	}


	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		> 
		createNamedNestedOrCollector(
			Collector_And_Named<
				MODEL,
				RESULT,
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {
		
		return new SQL_Collector_Or_NonProcessResult_Named<>(andClauses);
	}


	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
		createNamedNestedAndCollector(
			Collector_Or_Named<
				MODEL,
				RESULT,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> orClauses) {
		
		return new SQL_Collector_And_NonProcessResult_Named<>(orClauses);
	}


	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, 
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		>
		createAliasOrCollector() {
		
		return new SQL_Collector_Or_NonProcessResult_Alias<>(this);
	}


	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		>
		createAliasAndCollector() {
		
		return new SQL_Collector_And_NonProcessResult_Alias<>(this);
	}


	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		> 
		createAliasNestedOrCollector(
			Collector_And_Alias<
				MODEL,
				RESULT,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, 
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> andClauses) {
		
		return new SQL_Collector_Or_NonProcessResult_Alias<>(andClauses);
	}


	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		> createAliasNestedAndCollector(
				
			Collector_Or_Alias<
				MODEL,
				RESULT,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {
		
		return new SQL_Collector_And_NonProcessResult_Alias<>(orClauses);
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		throw new UnsupportedOperationException("TODO - no where-clause, must determine type in GroupBy, Collector_Named_Or_Alias or similar");
	}

	

	@Override
	public ISharedMapFunctions_Initial<
			MODEL,
			RESULT,
			
			IShortResult_Mapped_Single_Named<MODEL, RESULT>,
			IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
			
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Short, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Double, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultOps_String_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>, 
			ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_String_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>>

		map() {
		
		final MappingCollector mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
		

		final Supplier<IMappingCollector<MODEL, RESULT>>

		s1 = () -> {
			return new Short_Collector_Single_Mapped_Named<MODEL, RESULT>(
					select,
					new CollectedQueryResult_Mapped_Single(getResultType()),
					getQueryCollector());
			};

		final Supplier<IMappingCollector<MODEL, RESULT>>
				
		s2 = () -> {
			return 
					new Short_Collector_Single_Mapped_Alias<>(
					select,
					new CollectedQueryResult_Mapped_Single(getResultType()),
					getQueryCollector());
			
		};
		
		
		return new ResultMapper_ExpressionList_Initial_Undecided<>(s1, s2);
	}

	@Override
	public MappingCollector getMappingCollector() {
		
		final MappingCollector ret = getQueryCollector().getMappings();
		
		if (ret == null) {
			throw new IllegalStateException("mappings not set");
		}
		
		return ret;
	}
}
