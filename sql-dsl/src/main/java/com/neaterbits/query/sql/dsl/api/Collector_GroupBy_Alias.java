package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Collector_GroupBy_Alias<MODEL, RESULT> extends Collector_GroupBy<MODEL, RESULT>

	implements ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT> {

	Collector_GroupBy_Alias(Getter initial, Collector_Conditions<MODEL, RESULT, ?> collectorConditions) {
		super(initial, collectorConditions);
	}

	@Override
	public ISharedProcessResult_Having_Aggregate_Alias<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>> having() {
		throw new UnsupportedOperationException("TODO");
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>> having(
			ISupplierInteger getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>> having(
			ISupplierLong getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>> having(
			ISupplierBigDecimal getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>> having(
			ISupplierString getter) {
		throw new UnsupportedOperationException("TODO");
	}
}
