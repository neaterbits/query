package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_Or_MultiMapped_Named<MODEL, RESULT>
	extends Classic_Collector_Or_Named<MODEL, RESULT, IClassicLogical_Or_MultiMapped_Named<MODEL, RESULT>>

	implements IClassicLogical_Or_MultiMapped_Named<MODEL, RESULT> {

	Classic_Collector_Or_MultiMapped_Named(Classic_Collector_WhereOrJoin_Named_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}
}
