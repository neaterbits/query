package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Supplier;

final class Collector_GroupBy_Alias<MODEL, RESULT> extends Collector_GroupBy<MODEL, RESULT>

	implements ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT> {

	private Collector_Having_Alias<MODEL, RESULT> having;
	
	Collector_GroupBy_Alias(BaseQueryEntity<MODEL> last, Getter initial, Collector_Conditions<MODEL, RESULT, ?> collectorConditions) {
		super(last, initial, collectorConditions);
	}

	private void instantiateHaving() {
		if (this.having != null) {
			throw new IllegalStateException("'having' already called");
		}
		
		this.having = new Collector_Having_Alias<>(this);
	}
	

	private <R extends Comparable<R>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, R, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
		havingComparable(Supplier<R> supplier) {

		instantiateHaving();
		
		return new Collector_Condition_Comparative<>(having, new SupplierGetter(supplier));
	}
	
	@Override
	public ISharedProcessResult_Having_Aggregate_Alias<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>> having() {
		
		throw new UnsupportedOperationException("TODO");
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
		having(ISupplierInteger getter) {

		return havingComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
			having(ISupplierLong getter) {
		
		return havingComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
			having(ISupplierBigDecimal getter) {
		
		return havingComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
			having(ISupplierString getter) {

		instantiateHaving();
		
		return new Collector_Condition_String<>(having, new SupplierGetter(getter));
	}
}
