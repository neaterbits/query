package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_Single_Mapped_Alias_TypedJoin<MODEL, RESULT, JOIN_TYPE> 

		extends Short_Collector_Single_Mapped_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
		
		implements IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> {

	Short_Collector_Single_Mapped_Alias_TypedJoin(BaseQuery select, CollectedQueryResult_Mapped_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}
	
	

/*	
	
	@Override
	public <R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>
			innerJoin(Supplier<R> from, Supplier<R> to) {
				
		return addInnerJoin(from, to);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT, JOIN_TYPE>
			innerJoin(CollectionFunction<JOIN_TYPE, JOIN_TO> collection) {
				
		return addInnerJoin(collection);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> innerJoin(
			Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		
		return addInnerJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> innerJoin(
			CollectionFunction<JOIN_TYPE, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		return addInnerJoin(collection, consumer);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>
			leftJoin(Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to) {

		return addLeftJoin(from, to);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>
			leftJoin(CollectionFunction<JOIN_TYPE, JOIN_TO> collection) {

		return addLeftJoin(collection);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>
			leftJoin(
				Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to,
				Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		return addLeftJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE> leftJoin(
			CollectionFunction<JOIN_TYPE, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		return addLeftJoin(collection, consumer);
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT, 
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short,ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, 
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>
		> where() {

		throw new UnsupportedOperationException("TODO");
	}
	*/
	
	
	

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns, Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		throw new UnsupportedOperationException("No group-by for single queries");
	}



	@Override
	public ISharedFunctions_Transform_Initial_Alias<
				MODEL,
				RESULT,
				ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>, 
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>, 
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>> where() {
		throw new UnsupportedOperationException("TODO");
	}
}
