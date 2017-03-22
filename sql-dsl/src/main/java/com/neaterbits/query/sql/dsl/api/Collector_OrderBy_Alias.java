package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

final class Collector_OrderBy_Alias<MODEL, RESULT> extends Collector_OrderBy<MODEL, RESULT> 
		implements ISharedProcessResult_OrderBy_AfterSortOrder_Alias<MODEL, RESULT> {

	Collector_OrderBy_Alias(Collector_Base<MODEL> last, Getter initial,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		super(last, initial, collectorConditions);
	}

	
	@Override
	public <R> ISharedProcessResult_OrderBy_AfterSortOrder_Alias<MODEL, RESULT> and(Supplier<R> supplier) {
		
		addLastSortOrder();
		
		super.add(new SupplierGetter(supplier));
		
		return this;
	}

	@Override
	public ISharedProcessResult_After_OrderBy_Or_List_Alias<MODEL, RESULT> asc() {
		
		setSortOrderToAdd(ESortOrder.ASCENDING);
		
		return this;
	}

	@Override
	public ISharedProcessResult_After_OrderBy_Or_List_Alias<MODEL, RESULT> desc() {
		
		setSortOrderToAdd(ESortOrder.DESCENDING);
		
		return this;
	}
}
