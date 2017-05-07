package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class Short_Collector_Multi_Any_Any<MODEL, RESULT>
	extends Short_Collector_Any_Any_Any<
			MODEL,
			RESULT,
			
			ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT, IShortJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>,
						
			IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
			IShortResult_Mapped_Multi_Alias<MODEL, RESULT>,
			
			ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, Object, Object>,
			ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>,

			ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			// ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT>,
			IShortJoin_Condition_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>,

			Void>

	implements IShortResult_Multi_Instance<MODEL, RESULT> {


	private final ECollectionType collectionType;
	
	Short_Collector_Multi_Any_Any(BaseQuery select, SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(select, modelCompiler, null, selectSource);
		
		if (collectionType == null) {
			throw new IllegalArgumentException("collectionType == null");
		}

		this.collectionType = collectionType;
	}
	

	@Override
	CollectedQueryResult getResultWhenNotPresent() {
		// When string to build() so return multi-entity
		return new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
	}

	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereNamed() {
		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
		
		return new Short_Collector_Multi_Entity_Named<>(getQueryCollector(), result);
	}



	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereAlias() {
		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
		
		return new Short_Collector_Multi_Entity_Alias<>(getQueryCollector(), result);
	}



	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultNamed() {
		
		final CollectedQueryResult_Mapped_Multi collectedQueryResult = new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType);
		
		return new Short_Collector_Multi_Mapped_Named_Initial<MODEL, RESULT>(getQueryCollector(), collectedQueryResult);
	}


	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultAlias() {
		final CollectedQueryResult_Mapped_Multi collectedQueryResult = new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType);

		return new Short_Collector_Multi_Mapped_Alias<MODEL, RESULT>(getQueryCollector(), collectedQueryResult);
	}

	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
		createNamedOrCollector() {

		return new SQL_Collector_Or_MultiEntity_Named<>(getThisInitial());
	}

	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, 
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
	
		createNamedAndCollector() {
		
		return new SQL_Collector_And_MultiEntity_Named<>(getThisInitial());
	}


	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, 
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		>
		createAliasOrCollector() {

		return new SQL_Collector_Or_MultiEntity_Alias<>(getThisInitial());
	}

	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
	
		> createAliasAndCollector() {
		
		return new SQL_Collector_And_MultiEntity_Alias<>(getThisInitial());
	}


	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {

		throw new UnsupportedOperationException("TODO - must determine named or alias within groupBy");
	}



	@Override
	public ISharedMapFunctions_All_Undecided<
				MODEL,
				RESULT,
				
				IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
				IShortResult_Mapped_Multi_Alias<MODEL, RESULT>,
				IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>,

				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,

				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_String_Named<MODEL, RESULT, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				
				ISharedResultMapperTo<MODEL, RESULT, Byte, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigInteger, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Float, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,

				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_String_Alias<MODEL, RESULT, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,

				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				
				ISharedResultMapperTo<MODEL, RESULT, Byte, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigInteger, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Float, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_String_Undecided<MODEL, RESULT, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,

				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				
				ISharedResultMapperTo<MODEL, RESULT, Byte, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigInteger, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Float, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>
				
								
		>
			map() {
	
		final Supplier<IMappingCollector<MODEL, RESULT>>

		s1 = () -> {
			return new Short_Collector_Multi_Mapped_Named_Initial<MODEL, RESULT>(
					getQueryCollector(),
					new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType)
				);
		};

		final Supplier<IMappingCollector<MODEL, RESULT>>
				
		s2 = () -> {
			return new Short_Collector_Multi_Mapped_Alias<>(
						getQueryCollector(),
						new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType)
				);
		};
		
		
		return new ResultMapper_ExpressionList_Initial_Undecided<>(s1, s2);
	}


	@Override
	public ISharedFunctions_Transform_Initial_Named<
				MODEL,
				RESULT, 
				ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>,
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>, 

				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Byte, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Short, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>, 
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Long, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>, 
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigInteger, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>, 
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Float, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>, 
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Double, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>
		> where() {

		return whereNamed();
	}

	
	//**************************************************************************
	// Named entity join
	//**************************************************************************

	private <JOIN_FROM> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> namedJoinResult() {
		
		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
		
		return new Short_Collector_Multi_Entity_Named<>(getQueryCollector(), result);
	}
	
	
	
	
	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
		
		addInnerJoin(from, to);
		
		return namedJoinResult();
	}


	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		
		addInnerJoin(collection);
		
		return namedJoinResult();
	}


	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		addInnerJoin(from, to, consumer);

		return namedJoinResult();
	}


	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		addInnerJoin(collection, consumer);
		
		return namedJoinResult();
	}


	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {

		addLeftJoin(from, to);
		
		return namedJoinResult();
	}


	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		
		addLeftJoin(collection);
		
		return namedJoinResult();
	}


	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		addLeftJoin(from, to, consumer);
		
		return namedJoinResult();
	}


	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		addLeftJoin(collection, consumer);
		
		return namedJoinResult();
	}
}
