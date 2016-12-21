package com.neaterbits.query.sql.dsl.api;


/**
 * Where clause builder for any table
 * 
 * @author nhl
 *
 * @param <MODEL> model passed along and returned back
 * @param <RESULT> query result type
 */

public interface IClassicWhereClauseBuilderTable<MODEL, RESULT>
		extends ISharedWhereClauseBuilderTable<MODEL, RESULT, IClassicAndOrLogicalClausesTable<MODEL, RESULT>> {

}
