package com.neaterbits.query.sql.dsl.api;

final class Collector_Having_And_Named<MODEL, RESULT> extends Collector_And_Named<
	MODEL,
	RESULT,
	
	ISharedProcessResult_Having_And_Named<MODEL, RESULT>,
	ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
	ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>,
	
	Void>
	
	
	implements ISharedProcessResult_Having_And_Named<MODEL, RESULT> {
	
	Collector_Having_And_Named(Collector_Conditions_Initial<MODEL, RESULT, Void> last) {
		super(last);
	}
	
	@Override
	Collector_Or_Named<
		MODEL,
		RESULT,
		ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>,
		ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>, 
		ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void> createNestedOrCollector(
				
				
		Collector_And_Named<MODEL, RESULT, ISharedProcessResult_Having_And_Named<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void> andClauses) {
	
	return new Collector_Having_Or_Nested_Named<>(this);
	}
}
