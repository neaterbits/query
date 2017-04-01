package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;

public class Short_Collector_Single_Mapped_Named_TypedJoin<MODEL, RESULT, JOIN_TYPE>

	extends Short_Collector_Single_Mapped_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
	
	implements IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE> {

		
	Short_Collector_Single_Mapped_Named_TypedJoin(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> last) {
		super(last);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>
			innerJoin(Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to) {
				
		return addInnerJoin(from, to);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE> innerJoin(CollectionFunction<JOIN_TYPE, JOIN_TO> collection) {

		return addInnerJoin(collection);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>
			innerJoin(
					Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		return addInnerJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE> innerJoin(
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
	public <JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>
			leftJoin(
					CollectionFunction<JOIN_TYPE, JOIN_TO> collection,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		return addLeftJoin(collection, consumer);
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>, 
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, 
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>
		> where() {
		return whereNamed();
	}
}
