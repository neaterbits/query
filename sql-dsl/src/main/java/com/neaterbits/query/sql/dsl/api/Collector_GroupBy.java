package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_GroupBy<MODEL, RESULT>

		extends Collector_Fields<MODEL>
		implements
		ISharedProcessResult_List_Named<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT>>,
		ISharedProcessResult_OrderBy_Mapped_Named<MODEL, RESULT>,
		ISharedProcessResult_List_Alias<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT>>,
		ISharedProcessResult_OrderBy_Mapped_Alias<MODEL, RESULT>

 	{
	
	abstract Collector_Clause getHaving();

	private final int [] groupByColumns;
	
	private final Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions;
	
			
	Collector_GroupBy(Collector_Base<MODEL> last, Getter initial, Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		super(last);

		if (initial == null) {
			throw new IllegalArgumentException("initial == null");
		}
		
		super.add(initial);
		
		if (collectorConditions == null) {
			throw new IllegalArgumentException("collectorConditions == null");
		}

		this.collectorConditions = collectorConditions;
		this.groupByColumns = null;
	}
	
	Collector_GroupBy(Collector_Base<MODEL> last, int [] groupByColumns, Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		super(last);

		if (groupByColumns == null) {
			throw new IllegalArgumentException("groupByColumns == null");
		}
		
		this.collectorConditions = collectorConditions;
		this.groupByColumns = groupByColumns;
	}
	
	final int[] getGroupByColumns() {
		return groupByColumns;
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
