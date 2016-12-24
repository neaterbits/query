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
		extends ISharedWhereClauseBuilderTableAll<
			MODEL,
			RESULT,
			IClassicAndOrLogicalClausesTable<MODEL, RESULT>,
			
			ISharedClauseComparableCommonAll_Compilable<MODEL, RESULT, ? extends Comparable<?>, IClassicAndOrLogicalClausesTable<MODEL, RESULT>>,
			ISharedClauseComparableStringAll_Compilable<MODEL, RESULT, IClassicAndOrLogicalClausesTable<MODEL, RESULT>>> {

}