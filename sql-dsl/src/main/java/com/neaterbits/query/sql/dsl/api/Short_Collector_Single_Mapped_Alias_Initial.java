package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

final class Short_Collector_Single_Mapped_Alias_Initial<MODEL, RESULT> 
	
		extends Short_Collector_Single_Mapped_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
		implements 
				IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
				// when returned 'this' after where
				ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT> {

	Short_Collector_Single_Mapped_Alias_Initial(Collector_Query<MODEL> queryCollector, CollectedQueryResult_Mapped_Single result) {
		super(queryCollector, result);
	}

	/*
	@Override
	public ISharedFunctions_Alias_Initial<
			MODEL, 
			RESULT,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>, ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>, ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>> where() {
		return whereAlias();
	}
	*/

	@Override
	public ISharedMapFunctions_All_Alias<
				MODEL,
				RESULT,
				
				IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
				
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Date, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				
				ISharedResultOps_String_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Date, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>

		> map() {

		throw new UnsupportedOperationException("TODO");
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
				ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>>
	
		> where() {

		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <R extends Comparable<R>> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			innerJoin(Supplier<R> from, Supplier<R> to) {

		addInnerJoin(from, to);

		return joinResult();
	}

	@Override
	public <JOIN_TO> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			innerJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias) {

		addInnerJoin(collection, alias);
		
		return joinResult();
	}

	@Override
	public <R extends Comparable<R>> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			innerJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {

		
		addInnerJoin(from, to, consumer);
		
		return joinResult();
	}

	@Override
	public <JOIN_TO> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			innerJoin(
				CollectionSupplier<JOIN_TO> collection, JOIN_TO alias,
				Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {

		addInnerJoin(collection, alias, consumer);
		
		return joinResult();
	}

	@Override
	public <R extends Comparable<R>> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			leftJoin(Supplier<R> from, Supplier<R> to) {

		addLeftJoin(from, to);
		
		return joinResult();
	}

	@Override
	public <JOIN_TO> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			leftJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias) {

		
		addLeftJoin(collection, alias);
		
		return joinResult();
	}

	@Override
	public <R extends Comparable<R>> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
			leftJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {

		addLeftJoin(from, to, consumer);
		
		return joinResult();
	}

	@Override
	public <JOIN_TO> ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT> leftJoin(
			CollectionSupplier<JOIN_TO> collection, JOIN_TO alias,
			Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {

		addLeftJoin(collection, alias, consumer);
		
		return joinResult();
	}

	private <JOIN_FROM> Short_Collector_Single_Mapped_Alias_TypedJoin<MODEL, RESULT, JOIN_FROM> joinResult() {
		return new Short_Collector_Single_Mapped_Alias_TypedJoin<>(getThisInitial());
	}
}
