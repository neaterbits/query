package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_NonProcessResult_Named<MODEL, RESULT>

	extends IClassicLogical_Where_NonProcessResult_Named<MODEL, RESULT>,
			IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			IClassicLogical_AndOr_NonProcessResult_Named<MODEL, RESULT> {

}
