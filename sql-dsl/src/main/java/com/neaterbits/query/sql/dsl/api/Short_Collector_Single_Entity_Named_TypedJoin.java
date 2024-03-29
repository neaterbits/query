package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Function;

final class Short_Collector_Single_Entity_Named_TypedJoin<MODEL, RESULT, JOIN_FROM>
	extends Short_Collector_Single_Entity_Named_Base<MODEL, RESULT> 
	
	implements IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> {

	Short_Collector_Single_Entity_Named_TypedJoin(Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT> input) {
		super(input.getThisInitial(), new CollectedQueryResult_Entity_Single(input.getSelectSource()));
		
		//getQueryCollector().setResult(new CollectedQueryResult_Entity_Single(input.getSelectSource()));
	}
		
	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,

			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Byte, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigInteger, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Float, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>
		> where() {
		
		// TODO: probably move to superclass by implementing interface there
		throw new UnsupportedOperationException("TODO");
		
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>>
		IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {

		return addInnerJoin(from, to);
	}

	@Override
	public <JOIN_TO>
		IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {

		return addInnerJoin(collection);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
	
		innerJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return addInnerJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> 
	
		innerJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		
		return addInnerJoin(collection, consumer);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> 
	
		leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {

		return addLeftJoin(from, to);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
	
		leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {

		return addLeftJoin(collection);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		
		return addLeftJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
	
		leftJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return addLeftJoin(collection, consumer);
	}
}
