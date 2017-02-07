package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_NonProcessResult_Named<MODEL, RESULT>

	extends Classic_Collector_WhereOrJoin_Named_Base<
				MODEL,
				RESULT,
				IClassicJoin_Condition_NonProcessResult_Named<MODEL, RESULT, Object, Object>,
				
	
				IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				IClassicLogical_AndOr_NonProcessResult_Named<MODEL, RESULT>
				> 

		implements IClassicLogical_WhereOrJoin_NonProcessResult_Named<MODEL, RESULT> {

	Classic_Collector_WhereOrJoin_NonProcessResult_Named(BaseQueryEntity<MODEL> last) {
		super(last);
	}

	@Override
	Classic_Collector_Or_Named<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>> createOrCollector() {
		return new Classic_Collector_Or_NonProcessResult_Named<>(this);
	}

	@Override
	Classic_Collector_And_Named<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>> createAndCollector() {
		return new Classic_Collector_And_NonProcessResult_Named<>(this);
	}
}


