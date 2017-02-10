package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class Collector_OrderBy<MODEL, RESULT>
		extends Collector_Fields

		implements ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT> {

	static final ESortOrder DEFAULT_SORT_ORDER = ESortOrder.ASCENDING;
		
	private final Collector_Conditions<MODEL, RESULT> collectorConditions;
	
	// Keep state-variable on whether sort-order was added for last
	private ESortOrder sortOrderToAdd;
	
	
	private final List<ESortOrder> sortOrders;
	
	
	
	Collector_OrderBy(Function<?, ?> initial, Collector_Conditions<MODEL, RESULT> collectorConditions) {
		
		if (initial == null) {
			throw new IllegalArgumentException("initial == null");
		}
		
		super.add(initial);
		
		if (collectorConditions == null) {
			throw new IllegalArgumentException("collectorConditions == null");
		}
	
		this.collectorConditions = collectorConditions;
		
		this.sortOrders = new ArrayList<>();

		this.sortOrderToAdd = DEFAULT_SORT_ORDER;
	}

	private void addLastSortOrder() {
		sortOrders.add(sortOrderToAdd);
		sortOrderToAdd = DEFAULT_SORT_ORDER;
	}
	
	@Override
	public <T, R> ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT> and(Function<T, R> function) {

		addLastSortOrder();
		
		
		super.add(function);
		
		return this;
	}
	
	@Override
	public MODEL compile() {

		addLastSortOrder();
		
		return collectorConditions.compile();
	}

	@Override
	public ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> asc() {
		this.sortOrderToAdd = ESortOrder.ASCENDING;
		
		return this;
	}

	@Override
	public ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> desc() {
		this.sortOrderToAdd = ESortOrder.DESCENDING;
		
		return this;
	}

	ESortOrder [] getSortOrders() {
		return sortOrders.toArray(new ESortOrder[sortOrders.size()]);
	}
}