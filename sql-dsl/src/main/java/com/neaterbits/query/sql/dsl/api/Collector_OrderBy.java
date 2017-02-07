package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class Collector_OrderBy<MODEL, RESULT>
		extends Collector_Fields

		implements ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> {

	private final Collector_Conditions<MODEL, RESULT> collectorConditions;
	
	Collector_OrderBy(Function<?, ?> initial, Collector_Conditions<MODEL, RESULT> collectorConditions) {
		
		if (initial == null) {
			throw new IllegalArgumentException("initial == null");
		}
		
		super.add(initial);
		
		if (collectorConditions == null) {
			throw new IllegalArgumentException("collectorConditions == null");
		}
	
		this.collectorConditions = collectorConditions;
	}
	
	@Override
	public <T, R> ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> and(Function<T, R> function) {
		
		super.add(function);
		
		return this;
	}
	
	@Override
	public MODEL compile() {
		return collectorConditions.compile();
	}
}
