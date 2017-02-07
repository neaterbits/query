package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_ProcessResult_Named<MODEL, RESULT>

	extends 
		IClassicLogical_Where_ProcessResult_Named<MODEL, RESULT>,
		IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
		IClassicLogical_AndOr_ProcessResult_Named<MODEL, RESULT>,
		IClassicProcessResult_Named<MODEL, RESULT> {

}
