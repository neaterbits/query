package com.neaterbits.query.sql.dsl.api;

public interface WhereOrJoinBuilderAlias<MODEL, RESULT>
	extends WhereClauseBuilderAlias<MODEL, RESULT>,
			JoinClauseAlias<MODEL,RESULT> {

}
