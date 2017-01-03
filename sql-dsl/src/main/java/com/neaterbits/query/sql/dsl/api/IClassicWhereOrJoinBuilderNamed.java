package com.neaterbits.query.sql.dsl.api;

public interface IClassicWhereOrJoinBuilderNamed<MODEL, RESULT>
	extends IClassicWhereClauseBuilderNamed<MODEL, RESULT>,
			IClassicJoinClauseNamed<MODEL,RESULT>,
			ISharedCompileEndClause<MODEL> {

}
