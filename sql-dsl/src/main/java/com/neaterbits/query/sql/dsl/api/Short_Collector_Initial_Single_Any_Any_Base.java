package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT>
	extends Short_Collector_Any_Any_Any<
			MODEL,
			RESULT,
			
			IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, RESULT>,
			IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>,
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
	/*
			ISQLLogical_Where_SingleResult_Named_Base<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
	*/
			
			// IShortLogical_WhereOrJoin_SingleResult_Entity_Named_Initial<MODEL, RESULT>,
			
			
			//IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, RESULT>,
			
			//IShortResult_Single_Instance<MODEL, RESULT>,
			IShortResult_Mapped_Single_All<MODEL, RESULT>,
			IShortJoin_Single_Named_Initial<MODEL, RESULT>,
	
			IShortJoin_Single_Alias_Initial<MODEL, RESULT>,
			IMappingCollector<MODEL, RESULT> {
	

	private final BaseQuery select;
	
	@Deprecated
	Short_Collector_Initial_Single_Any_Any_Base(BaseQuery select, ModelCompiler<MODEL> modelCompiler, CollectedQueryResult result, SharedSelectSource selectSource) {
		super(select, modelCompiler, result, selectSource);
		
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
		
		return new Short_Collector_Single_Mapped_Named_Initial<MODEL, RESULT>(getQueryCollector(), collectedQueryResult);
	}


	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultAlias() {
		final CollectedQueryResult_Mapped_Single collectedQueryResult = new CollectedQueryResult_Mapped_Single(getResultType());

		return new Short_Collector_Single_Mapped_Alias_Initial<MODEL, RESULT>(getQueryCollector(), collectedQueryResult);
	}
	
	
	

	
	
	
	// ******************* !!! IMPORTANT must switch to one that implements AndOr interface !!! *******************


	/*
	@Override
	public <T> IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, T> joinRoot(Class<T> type) {
		
		// Just for type-checking and readability, telling where join starts from, if not from root 
		
		return (IShortLogical_WhereOrJoin_SingleResult_Entity_Named)this;
	}
	*/

	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereNamed() {
		
		final SharedSelectSource_Named selectSource = (SharedSelectSource_Named)getSelectSource();
		
		return new Short_Collector_Initial_Single_Entity_Named<>(select, getModelCompiler(), new CollectedQueryResult_Entity_Single(selectSource), selectSource);
	}

	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereAlias() {
		
		final SharedSelectSource_Alias selectSource = (SharedSelectSource_Alias)getSelectSource();

		return new Short_Collector_Initial_Single_Entity_Alias<>(select, getModelCompiler(), new CollectedQueryResult_Entity_Single(selectSource), selectSource);
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
	
		return new SQL_Collector_Or_NonProcessResult_Named<>(getThisInitial());
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

		return new SQL_Collector_And_NonProcessResult_Named<>(getThisInitial());
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
		
		return new SQL_Collector_Or_NonProcessResult_Alias<>(getThisInitial());
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
		
		return new SQL_Collector_And_NonProcessResult_Alias<>(getThisInitial());
	}


	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		throw new UnsupportedOperationException("TODO - no where-clause, must determine type in GroupBy, Collector_Named_Or_Alias or similar");
	}

	

	@Override
	public ISharedMap_Functions_All_Undecided<
			MODEL,
			RESULT,
			
			IShortResult_Mapped_Single_Named<MODEL, RESULT>,
			IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
			IShortResult_Mapped_Single_Undecided<MODEL, RESULT>,
			
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,

			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>, 
			ISharedMap_To<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,

			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Undecided<MODEL, RESULT, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IShortResult_Mapped_Single_Undecided<MODEL, RESULT>>
			
			>

		map() {
		
		final MappingCollector mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
		

		final Supplier<IMappingCollector<MODEL, RESULT>>

		s1 = () -> {
			return new Short_Collector_Single_Mapped_Named_Initial<MODEL, RESULT>(
					getQueryCollector(),
					new CollectedQueryResult_Mapped_Single(getResultType())
				);
		};

		final Supplier<IMappingCollector<MODEL, RESULT>>
				
		s2 = () -> {
			return new Short_Collector_Single_Mapped_Alias_Initial<>(
						getQueryCollector(),
						new CollectedQueryResult_Mapped_Single(getResultType())
				);
		};

		final Supplier<IMappingCollector<MODEL, RESULT>>
		
		s3 = () -> {
			return new Short_Collector_Single_Mapped_Undecided_Initial<>(
						getQueryCollector(),
						new CollectedQueryResult_Mapped_Single(getResultType())
				);
		};

		return new ResultMapper_ExpressionList_Initial_Undecided<>(s1, s2, s3);
	}

	@Override
	public MappingCollector getMappingCollector() {
		
		final MappingCollector ret = getQueryCollector().getMappings();
		
		if (ret == null) {
			throw new IllegalStateException("mappings not set");
		}
		
		return ret;
	}

	abstract <JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> namedTypedJoinCollector();

	abstract <JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> aliasTypedJoinCollector();

	@Override
	public final <JOIN_FROM, JOIN_TO, R extends Comparable<R>>
		IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
		
		addInnerJoin(from, to);

		return namedTypedJoinCollector();
	}

	@Override
	public final <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
		innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		
		addInnerJoin(collection);

		return namedTypedJoinCollector();
	}

	@Override
	public final <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
	
		innerJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		addInnerJoin(from, to, consumer);
		
		return namedTypedJoinCollector();
	}

	@Override
	public final <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
		innerJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		addInnerJoin(collection, consumer);

		return namedTypedJoinCollector();
	}

	@Override
	public final <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> 
	
		leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {

		addLeftJoin(from, to);
		
		return namedTypedJoinCollector();
	}

	@Override
	public final <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
	
		leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		
		addLeftJoin(collection);
		
		return namedTypedJoinCollector();
	}

	@Override
	public final <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
	
		leftJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		addLeftJoin(from, to, consumer);
		
		return namedTypedJoinCollector();
	}

	@Override
	public final <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
		leftJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		addLeftJoin(collection, consumer);
		
		return namedTypedJoinCollector();
	}



	@Override
	public final <R extends Comparable<R>> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			innerJoin(Supplier<R> from, Supplier<R> to) {

		addInnerJoin(from, to);
		
		return aliasTypedJoinCollector();
	}



	@Override
	public final <JOIN_TO> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			innerJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias) {
		
		addInnerJoin(collection, alias);

		return aliasTypedJoinCollector();
	}



	@Override
	public final <R extends Comparable<R>> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			innerJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {

		addInnerJoin(from, to, consumer);

		return aliasTypedJoinCollector();
	}



	@Override
	public final <JOIN_TO> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			innerJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		
		addInnerJoin(collection, alias, consumer);

		return aliasTypedJoinCollector();
	}



	@Override
	public final <R extends Comparable<R>> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			leftJoin(Supplier<R> from, Supplier<R> to) {

		addLeftJoin(from, to);
		
		return aliasTypedJoinCollector();
	}



	@Override
	public final <JOIN_TO> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			leftJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias) {
		
		addLeftJoin(collection, alias);

		return aliasTypedJoinCollector();
	}


	@Override
	public final <R extends Comparable<R>> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			leftJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {

		addLeftJoin(from, to, consumer);
		
		return aliasTypedJoinCollector();
	}



	@Override
	public final <JOIN_TO> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			leftJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {

		addLeftJoin(collection, alias, consumer);

		return aliasTypedJoinCollector();
	}
}
