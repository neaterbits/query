package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

final class Collector_GroupBy_Named<MODEL, RESULT> extends Collector_GroupBy<MODEL, RESULT>
		
	implements ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> {

	private Collector_Having_Named<MODEL, RESULT> having;

	
	@Override
	Collector_Clause getHaving() {
		return having != null ? having.clauseCollector : null;
	}
	
	Collector_GroupBy_Named(Collector_Base<MODEL> last, Getter initial, Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		super(last, initial, collectorConditions);
	}

	Collector_GroupBy_Named(Collector_Base<MODEL> last, int[] groupByColumns, Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		super(last, groupByColumns, collectorConditions);
	}

	private void instantiateHaving() {
		if (this.having != null) {
			throw new IllegalStateException("'having' already called");
		}
		
		this.having = new Collector_Having_Named<>(this);
	}
	

	private <R extends Comparable<R>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, R, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		havingComparable(Function<?, R> function) {

		instantiateHaving();
		
		return new Collector_Condition_Comparative<>(having, new FieldExpression(function));
	}
	

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionInteger<T> getter) {

		return havingComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionLong<T> getter) {
		
		return havingComparable(getter);
	}
	
	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
			having(IFunctionBigDecimal<T> getter) {

		return havingComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(StringFunction<T> getter) {
		
		instantiateHaving();
		
		return new Collector_Condition_String<>(having, new FieldExpression(getter));
	}

	@Override
	public ISharedProcessResult_Having_Aggregate_Named<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>> having() {
		throw new UnsupportedOperationException("TODO");
	}
}
