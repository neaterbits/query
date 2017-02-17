package com.neaterbits.query.sql.dsl.api;

final class Collector_Having_Or_Nested_Alias<MODEL, RESULT> extends Collector_Or_Alias<
		MODEL,
		RESULT,
		ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>,
		ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>,
		ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>,
		Void> 

	implements ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT> {

	Collector_Having_Or_Nested_Alias(Collector_Base<MODEL> qe) {
		super(qe);
	}

	Collector_Having_Or_Nested_Alias(Collector_Conditions_Initial<MODEL, RESULT, Void> last) {
		super(last);
	}

	@Override
	Collector_And_Alias<MODEL, RESULT, ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>, Void> createNestedAndCollector(
			Collector_Or_Alias<MODEL, RESULT, ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>, Void> orClauses) {
		return new Collector_Having_And_Nested_Alias<>(this);
	}
}
