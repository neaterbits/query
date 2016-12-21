package com.neaterbits.query.sql.dsl.api;

public interface WhereOrJoinBuilderTable<MODEL, RESULT>
	extends IClassicWhereClauseBuilderTable<MODEL, RESULT>,
			JoinClauseTable<MODEL,RESULT> {

}
