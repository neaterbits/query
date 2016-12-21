package com.neaterbits.query.sql.dsl.api;

public interface IClassicWhereOrJoinBuilderTable<MODEL, RESULT>
	extends IClassicWhereClauseBuilderTable<MODEL, RESULT>,
			IClassicJoinClauseTable<MODEL,RESULT> {

}
