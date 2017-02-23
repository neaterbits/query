package com.neaterbits.query.sql.dsl.api;

final class Collector_Having_Alias<MODEL, RESULT> extends Collector_And_Or_Alias<
		MODEL,
		RESULT,
		ISharedProcessResult_Having_And_Alias<MODEL, RESULT>,
		ISharedProcessResult_Having_Or_Alias<MODEL, RESULT>,
		ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>,
		ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>,
		Void>	 

		implements ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT> {

	
	
	Collector_Having_Alias(Collector_Base<MODEL> last) {
		super(last, EConditionsClause.HAVING);
	}

	@Override
	Collector_Or_Alias<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_Or_Alias<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>, Void>
	
		createOrCollector() {
		
		return new Collector_Having_Or_Alias<>(this);
	}

	@Override
	Collector_And_Alias<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_And_Alias<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>, Void>
	
		createAndCollector() {

		return new Collector_Having_And_Alias<>(this);
	}

	@Override
	Collector_Or_Alias<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>,
				Void>
	
		createNestedOrCollector(Collector_And_Alias<MODEL, RESULT, ISharedProcessResult_Having_And_Alias<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>, Void> andClauses) {

		return new Collector_Having_Or_Nested_Alias<>(andClauses);
	}

	@Override
	Collector_And_Alias<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>,
				Void>
	
		createNestedAndCollector(Collector_Or_Alias<MODEL, RESULT, ISharedProcessResult_Having_Or_Alias<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>, Void> orClauses) {

		return new Collector_Having_And_Nested_Alias<>(orClauses);
	}
}
