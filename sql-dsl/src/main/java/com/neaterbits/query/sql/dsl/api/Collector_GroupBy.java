package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_GroupBy<MODEL, RESULT>

		extends Collector_Fields
		implements
		ISharedProcessResult_List_Named<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT>>,
		ISharedProcessResult_OrderBy_Mapped_Named<MODEL, RESULT>,
		ISharedProcessResult_List_Alias<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT>>,
		ISharedProcessResult_OrderBy_Mapped_Alias<MODEL, RESULT>

 	{

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
	@SuppressWarnings("unchecked")
	public final <T, R> ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> and(Function<T, R> function) {
		
		super.add(new FunctionGetter(function));
		
		return (ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT>)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <R> ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT> and(Supplier<R> function) {

		super.add(new SupplierGetter(function));
		
		return (ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT>)this;
	}


	@Override
	public final MODEL compile() {
		return collectorConditions.compile();
	}

	@Override
	public final <T, R> ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT> orderBy(Function<T, R> field) {
		return collectorConditions.orderBy(field);
	}
	
	@Override
	public final <R> ISharedProcessResult_OrderBy_AfterSortOrder_Alias<MODEL, RESULT> orderBy(Supplier<R> field) {
		return collectorConditions.orderBy(field);
	}

	@Override
	public final ISharedCompileEndClause<MODEL> orderBy(int... resultColumns) {
		return collectorConditions.orderBy(resultColumns);
	}
}
