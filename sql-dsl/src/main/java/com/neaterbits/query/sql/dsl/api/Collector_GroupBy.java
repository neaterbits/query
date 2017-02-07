package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class Collector_GroupBy<MODEL, RESULT>

		extends Collector_Fields
		implements ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> {

	private final Collector_Conditions<MODEL, RESULT> collectorConditions;

	Collector_GroupBy(Function<?, ?> initial, Collector_Conditions<MODEL, RESULT> collectorConditions) {
		
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
	public <T, R> ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT> and(Function<T, R> function) {
		
		super.add(function);
		
		return this;
	}

	@Override
	public MODEL compile() {
		return collectorConditions.compile();
	}

	@Override
	public ISharedProcessResult_OrderBy_Named<MODEL, RESULT> having() {
		return collectorConditions.having();
	}

	@Override
	public <T, R> ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> orderBy(Function<T, R> field) {
		return collectorConditions.orderBy(field);
	}

	@Override
	public ISharedCompileEndClause<MODEL> orderBy(int... resultColumns) {
		return collectorConditions.orderBy(resultColumns);
	}
}
