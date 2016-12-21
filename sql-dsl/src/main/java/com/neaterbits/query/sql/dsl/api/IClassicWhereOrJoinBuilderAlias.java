package com.neaterbits.query.sql.dsl.api;

public interface IClassicWhereOrJoinBuilderAlias<MODEL, RESULT>
	extends IClassicWhereClauseBuilderAlias<MODEL, RESULT>,
			IClassicJoinClauseAlias<MODEL,RESULT> {

}
