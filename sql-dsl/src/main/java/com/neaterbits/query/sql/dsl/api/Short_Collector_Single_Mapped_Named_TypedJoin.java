package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class Short_Collector_Single_Mapped_Named_TypedJoin<MODEL, RESULT, JOIN_TYPE>

	extends Short_Collector_Single_Mapped_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
	
	implements IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>,
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT> {

		
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
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, Byte, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, Short, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, BigInteger, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, Float, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>
		> where() {
		return whereNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>
		> and() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,

			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedComparison_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>
	
		> or() {
		throw new UnsupportedOperationException("TODO");
	}
}
