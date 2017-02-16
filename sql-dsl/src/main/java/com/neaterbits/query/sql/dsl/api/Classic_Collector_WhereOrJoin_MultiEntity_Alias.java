package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>

		extends
		Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, IClassicJoin_Condition_MultiEntity_Alias<MODEL, RESULT>,

				IClassicLogical_And_MultiEntity_Alias<MODEL, RESULT>,
				IClassicLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
				IClassicLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>>

		implements IClassicLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT> {

	Classic_Collector_WhereOrJoin_MultiEntity_Alias(BaseQueryEntity<MODEL> last) {
		super(last);
	}

	@Override
	Classic_Collector_Or_Alias<MODEL, RESULT, IClassicLogical_Or_MultiEntity_Alias<MODEL, RESULT>> createOrCollector() {
		return new Classic_Collector_Or_MultiEntity_Alias<>(this);
	}

	@Override
	Classic_Collector_And_Alias<MODEL, RESULT, IClassicLogical_And_MultiEntity_Alias<MODEL, RESULT>> createAndCollector() {
		return new Classic_Collector_And_MultiEntity_Alias<>(this);
	}
}