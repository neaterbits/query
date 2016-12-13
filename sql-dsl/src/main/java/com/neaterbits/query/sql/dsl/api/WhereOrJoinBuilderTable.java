package com.neaterbits.query.sql.dsl.api;

public interface WhereOrJoinBuilderTable<MODEL, RESULT>
	extends WhereClauseBuilderTable<MODEL, RESULT>,
			JoinClauseTable<MODEL,RESULT> {

}
