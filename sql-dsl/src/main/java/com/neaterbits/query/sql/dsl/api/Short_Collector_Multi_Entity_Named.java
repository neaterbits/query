package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;

final class Short_Collector_Multi_Entity_Named<MODEL, RESULT, JOIN_TYPE>
	extends Short_Collector_Multi_Entity_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>


	implements ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>,
		IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
{

	Short_Collector_Multi_Entity_Named(BaseQuery select, CollectedQueryResult_Entity_Multi result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}
	
	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
	

	@Override
	public ISharedFunctions_Transform_Initial_Named<
				MODEL,
				RESULT,
				ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short,
				ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>>> 
	
			or() {
		
		return orNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL, 
			RESULT,
			ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>>> and() {
		
		return andNamed();
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
			innerJoin(Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to) {

		return addInnerJoin(from, to);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
			innerJoin(CollectionFunction<JOIN_TYPE, JOIN_TO> collection) {
				
		return addInnerJoin(collection);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
			innerJoin(Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		return addInnerJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
			innerJoin(CollectionFunction<JOIN_TYPE, JOIN_TO> collection, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		return addInnerJoin(collection, consumer);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
			leftJoin(Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to) {
				
		return addLeftJoin(from, to);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
			leftJoin(CollectionFunction<JOIN_TYPE, JOIN_TO> collection) {

		return addLeftJoin(collection);
	}

	@Override
	public <JOIN_TO, R extends Comparable<R>> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
			leftJoin(Function<JOIN_TYPE, R> from, Function<JOIN_TO, R> to, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return addLeftJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_TYPE>
			leftJoin(CollectionFunction<JOIN_TYPE, JOIN_TO> collection, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		return addLeftJoin(collection, consumer);
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			
			ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>
		> where() {

		throw new UnsupportedOperationException("TODO");
	}
}
