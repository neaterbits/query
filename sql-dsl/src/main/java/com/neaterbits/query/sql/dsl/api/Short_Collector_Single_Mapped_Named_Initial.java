package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;

final class Short_Collector_Single_Mapped_Named_Initial<MODEL, RESULT>
		extends Short_Collector_Single_Mapped_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>

		implements IShortResult_Mapped_Single_Named<MODEL, RESULT>,
				// when returning 'this' after where
				ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT> {

	private final BaseQuery select;
	
	Short_Collector_Single_Mapped_Named_Initial(BaseQuery select, CollectedQueryResult_Mapped_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
		
		this.select = select;
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
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>>> where() {
		return whereNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
				MODEL,
				RESULT, 
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, 
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
				
				ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>> and() {
		return andNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
				MODEL,
				RESULT,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>> or() {
		
		return orNamed();
	}

	@Override
	public ISharedMapFunctions_All_Named<
				MODEL,
				RESULT,
				
				IShortResult_Mapped_Single_Named<MODEL, RESULT>,

				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Short, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Double, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Date, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultOps_String_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
					
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Date, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Single_Named<MODEL, RESULT>>
	
		>
	
			map() {
		
		
		return new Collector_MapFunctions_ExpressionList_Named<>(this);
	}

	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
				innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
					
		addInnerJoin(from, to);

		return joinResult();
	}

	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
				innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
					
		addInnerJoin(collection);

		return joinResult();
	}

	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
				innerJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		addInnerJoin(from, to, consumer);

		return joinResult();
	}

	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
				innerJoin(
						
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		addInnerJoin(collection, consumer);

		return joinResult();
	}

	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> 
			leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
				
		addLeftJoin(from, to);

		return joinResult();
	}

	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
			leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {

		addLeftJoin(collection);

		return joinResult();
	}

	@Override
	public <JOIN_FROM, JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM>
		leftJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		addLeftJoin(from, to, consumer);

		return joinResult();
	}

	@Override
	public <JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		addLeftJoin(collection, consumer);
		
		return joinResult();
	}

	private <JOIN_FROM> Short_Collector_Single_Mapped_Named_TypedJoin<MODEL, RESULT, JOIN_FROM> joinResult() {
		return new Short_Collector_Single_Mapped_Named_TypedJoin<>(select, (CollectedQueryResult_Mapped_Single)getQueryCollector().getResult(), getQueryCollector());
	}
}