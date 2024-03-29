package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Short_Collector_Initial_Single_Aggregate_Named<RESULT> 
		extends Short_Collector_Initial_Single_Aggregate_Any<SingleBuilt<RESULT>, RESULT> 

		implements 
		 	IShortBuilt_Numeric_Named<RESULT>,
		 	ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT> {

	Short_Collector_Initial_Single_Aggregate_Named(
				BaseQuery query,
				ModelCompiler<SingleBuilt<RESULT>> modelCompiler,
				CollectedQueryResult_Aggregate result) {
		
		super(query, modelCompiler, result);
	}

	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM> namedTypedJoinCollector() {
		return new Short_Collector_Single_Aggregate_Named_TypedJoin<>(this);
	}
	
	
	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Alias<SingleBuilt<RESULT>, RESULT> aliasTypedJoinCollector() {
		throw new UnsupportedOperationException("N/A");
	}
	
	
	/*
	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM>
			innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
				
		return addInnerJoin(from, to);
	}



	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM>
			innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		return addInnerJoin(collection);
	}



	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM> innerJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<SingleBuilt<RESULT>, RESULT, JOIN_TO, Void>> consumer) {
		
		return addInnerJoin(from, to, consumer);
	}



	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM> innerJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<SingleBuilt<RESULT>, RESULT, JOIN_TO, Void>> consumer) {
		
		return addInnerJoin(collection, consumer);
	}



	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM>
			leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
				
		return addLeftJoin(from, to);
	}



	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM>
			leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
				
		return addLeftJoin(collection);
	}



	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM> leftJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<SingleBuilt<RESULT>, RESULT, JOIN_TO, Void>> consumer) {

		return addLeftJoin(from, to, consumer);
	}



	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<SingleBuilt<RESULT>, RESULT, JOIN_FROM> leftJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<SingleBuilt<RESULT>, RESULT, JOIN_TO, Void>> consumer) {

		return addLeftJoin(collection, consumer);
	}
	*/


	@Override
	public ISharedFunctions_Transform_Initial_Named<
				SingleBuilt<RESULT>,
				RESULT,
				
				ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>, 
				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,

				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, Byte, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, BigInteger, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, Float, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>,
				ISharedCondition_OpsAndComp_Comparable_Named<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>, 
				ISharedCondition_OpsAndComp_String_Named<SingleBuilt<RESULT>, RESULT, ISQLLogical_AndOr_SingleResult_Named<SingleBuilt<RESULT>, RESULT>>

		> where() {
		
		return whereNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			SingleBuilt<RESULT>,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, 
			
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Byte, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>, 
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigInteger, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Float, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_String_All<SingleBuilt<RESULT>, RESULT, ISQLLogical_And_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>

		> and() {
		
		return andNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			SingleBuilt<RESULT>,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Byte, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Short, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Long, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigInteger, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Float, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, Double, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_Common_All<SingleBuilt<RESULT>, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>,
			ISharedComparison_Comparable_String_All<SingleBuilt<RESULT>, RESULT, ISQLLogical_Or_NonProcessResult_Named<SingleBuilt<RESULT>, RESULT>>
		> or() {
		
		return orNamed();
	}
}
