package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class Collector_OrderBy_Named<MODEL, RESULT> extends Collector_OrderBy<MODEL, RESULT> 
		implements ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT> {

	Collector_OrderBy_Named(Collector_Base<MODEL> last, Getter initial,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		super(last, initial, collectorConditions);
	}
	

	@Override
	public <T, R> ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT> and(Function<T, R> function) {

		addLastSortOrder();
		
		super.add(new FunctionGetter(function));
		
		return this;
	}
	
	@Override
	public ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> asc() {
		
		setSortOrderToAdd(ESortOrder.ASCENDING);
		
		return this;
	}

	@Override
	public ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> desc() {
		
		setSortOrderToAdd(ESortOrder.DESCENDING);
		
		return this;
	}
}
