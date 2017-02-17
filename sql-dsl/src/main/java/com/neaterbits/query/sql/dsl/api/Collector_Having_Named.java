package com.neaterbits.query.sql.dsl.api;

final class Collector_Having_Named<MODEL, RESULT> extends Collector_And_Or_Named<
		MODEL,
		RESULT,
		ISharedProcessResult_Having_And_Named<MODEL, RESULT>,
		ISharedProcessResult_Having_Or_Named<MODEL, RESULT>,
		ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
		ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>,
		Void>	 {

	
	
	Collector_Having_Named(Collector_Base<MODEL> last) {
		super(last);
	}

	Collector_Having_Named(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler, Collector_Clause collector) {
		super(queryCollector, modelCompiler, collector);
	}

	@Override
	Collector_Or_Named<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_Or_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void>
	
		createOrCollector() {
		
		return new Collector_Having_Or_Named<>(this);
	}

	@Override
	Collector_And_Named<
				MODEL,
				RESULT,
				ISharedProcessResult_Having_And_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void>
	
		createAndCollector() {

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
	
		createNestedOrCollector(Collector_And_Named<MODEL, RESULT, ISharedProcessResult_Having_And_Named<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void> andClauses) {

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
	
		createNestedAndCollector(Collector_Or_Named<MODEL, RESULT, ISharedProcessResult_Having_Or_Named<MODEL, RESULT>, ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>, ISharedProcessResult_Having_Or_Nested_Named<MODEL, RESULT>, Void> orClauses) {

		return new Collector_Having_And_Nested_Named<>(orClauses);
	}
}
