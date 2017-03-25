package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

abstract class Short_Collector_Single_Aggregate_Any_TypedJoin<MODEL, RESULT, JOIN_FROM, RET_TYPE> 

	extends Short_Collector_WhereOrJoin_Base<
			MODEL,
			RESULT,
			
			IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, RESULT>,
			IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>,
			/*
			ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
			*/
						/*
			IShortResult_Mapped_Single_Named<MODEL, RESULT>,
			IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
			*/
			
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

	implements IShortJoin_Named_Base<MODEL, RESULT, JOIN_FROM, RET_TYPE>

{
	Short_Collector_Single_Aggregate_Any_TypedJoin(Short_Collector_Initial_Single_Aggregate_Any<MODEL, RESULT> initial) {
		super(initial.getQueryCollector(), initial.clauseCollector);
	}

	@Override
	final Collector_Or_Named<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedOrCollector() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	final Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedAndCollector() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	final Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createAliasOrCollector() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	final Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createAliasAndCollector() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {

		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> RET_TYPE
		innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
		
		return addInnerJoin(from, to);
	}

	@Override
	public final <JOIN_TO> RET_TYPE
		innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		
		return addInnerJoin(collection);
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> RET_TYPE
		innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return addInnerJoin(from, to, consumer);
	}

	@Override
	public final <JOIN_TO> RET_TYPE
		innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return addInnerJoin(collection, consumer);
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> RET_TYPE
		leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
		
		return addLeftJoin(from, to);
	}

	@Override
	public final <JOIN_TO> RET_TYPE
		leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		
		return addLeftJoin(collection);
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> RET_TYPE
		leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return addLeftJoin(from, to, consumer);
	}

	@Override
	public final <JOIN_TO> RET_TYPE
		leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return addLeftJoin(collection);
	}
}
