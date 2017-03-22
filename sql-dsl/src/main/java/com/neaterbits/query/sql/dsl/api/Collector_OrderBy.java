package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

abstract class Collector_OrderBy<MODEL, RESULT> extends Collector_Fields<MODEL> 
	{

	static final ESortOrder DEFAULT_SORT_ORDER = ESortOrder.ASCENDING;
		
	private final Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions;
	
	// Keep state-variable on whether sort-order was added for last
	private ESortOrder sortOrderToAdd;
	
	
	private final List<ESortOrder> sortOrders;
	
	
	
	Collector_OrderBy(Collector_Base<MODEL> last, Getter initial, Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		super(last);
		
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

	final void addLastSortOrder() {
		sortOrders.add(sortOrderToAdd);
		sortOrderToAdd = DEFAULT_SORT_ORDER;
	}
	
	final void setSortOrderToAdd(ESortOrder sortOrder) {

		if (sortOrder == null) {
			throw new IllegalArgumentException("sortOrder == null");
		}

		this.sortOrderToAdd = sortOrder;
	}
	

	// @Override interface in subclass
	public final MODEL build() {

		addLastSortOrder();
		
		return collectorConditions.build();
	}
	
	ESortOrder [] getSortOrders() {
		return sortOrders.toArray(new ESortOrder[sortOrders.size()]);
	}
}
