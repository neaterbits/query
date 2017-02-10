package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class Collector_GroupBy<MODEL, RESULT>

		extends Collector_Fields
		implements
			ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT>{

	private final Collector_Conditions<MODEL, RESULT, ?> collectorConditions;

	
	Collector_GroupBy(Getter initial, Collector_Conditions<MODEL, RESULT, ?> collectorConditions) {
		
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
	public <T, R> ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> and(Function<T, R> function) {
		
		super.add(new FunctionGetter(function));
		
		return this;
	}

	@Override
	public <R> ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT> and(Supplier<R> function) {

		super.add(new SupplierGetter(function));
		
		return this;
	}


	@Override
	public MODEL compile() {
		return collectorConditions.compile();
	}

	@Override
	public ISharedProcessResult_OrderBy_Mapped_Named<MODEL, RESULT> having(int foo) {
		return collectorConditions.having(foo);
	}

	@Override
	public ISharedProcessResult_OrderBy_Mapped_Alias<MODEL, RESULT> having(String bar) {
		return collectorConditions.having(bar);
	}

	@Override
	public <T, R> ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT> orderBy(Function<T, R> field) {
		return collectorConditions.orderBy(field);
	}
	
	@Override
	public <R> ISharedProcessResult_OrderBy_AfterSortOrder_Alias<MODEL, RESULT> orderBy(Supplier<R> field) {
		return collectorConditions.orderBy(field);
	}

	@Override
	public ISharedCompileEndClause<MODEL> orderBy(int... resultColumns) {
		return collectorConditions.orderBy(resultColumns);
	}
}
