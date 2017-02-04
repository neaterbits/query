package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_And_ProcessResult_Alias<MODEL, RESULT>

	extends Classic_Collector_And_Alias<MODEL, RESULT, IClassicLogical_And_ProcessResult_Alias<MODEL, RESULT>>
	
	implements IClassicLogical_And_ProcessResult_Alias<MODEL, RESULT> {
	
	Classic_Collector_And_ProcessResult_Alias(
		Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}
	
	Classic_Collector_And_ProcessResult_Alias(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}
}
