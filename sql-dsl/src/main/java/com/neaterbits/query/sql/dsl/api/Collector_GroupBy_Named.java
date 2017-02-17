package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Collector_GroupBy_Named<MODEL, RESULT> extends Collector_GroupBy<MODEL, RESULT>
		
	implements ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> {

	//private Collector_Having_Named having;

	
	Collector_GroupBy_Named(Collector_Base<MODEL> last, Getter initial, Collector_Conditions<MODEL, RESULT, ?> collectorConditions) {
		super(last, initial, collectorConditions);
	}
	
	

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionInteger<T> getter) {

		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionLong<T> getter) {
		
		throw new UnsupportedOperationException("TODO");
	}
	
	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
			having(IFunctionBigDecimal<T> getter) {
		throw new UnsupportedOperationException("TODO");	
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(StringFunction<T> getter) {
		
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedProcessResult_Having_Aggregate_Named<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>> having() {
		throw new UnsupportedOperationException("TODO");
	}
}
