package com.neaterbits.query.sql.dsl.api;

final class Collector_Having_Named<MODEL, RESULT> extends Collector_And_Or_Named<
		MODEL,
		RESULT,
		ISharedProcessResult_Having_And_Named<MODEL, RESULT>,
		ISharedProcessResult_Having_Or_Named<MODEL, RESULT>,
		ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
		ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>,
		Void>	

	implements ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT> {

	
	
	Collector_Having_Named(Collector_GroupBy<MODEL, RESULT> last) {
		super(last, EConditionsClause.HAVING);
	}

	@Override
	Collector_Or_Named<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_Or_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>,
				Void>
	
		createNamedOrCollector() {
		
		return new Collector_Having_Or_Named<>(this);
	}

	@Override
	Collector_And_Named<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_And_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void>
	
		createNamedAndCollector() {

		return new Collector_Having_And_Named<>(this);
	}

	@Override
	Collector_Or_Named<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>,
				Void>
	
		createNamedNestedOrCollector(Collector_And_Named<MODEL, RESULT, ISharedProcessResult_Having_And_Named<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void> andClauses) {

		return new Collector_Having_Or_Nested_Named<>(andClauses);
	}

	@Override
	Collector_And_Named<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>,
				Void>
	
		createNamedNestedAndCollector(Collector_Or_Named<MODEL, RESULT, ISharedProcessResult_Having_Or_Named<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void> orClauses) {

		return new Collector_Having_And_Nested_Named<>(orClauses);
	}
}
