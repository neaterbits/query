package com.neaterbits.query.sql.dsl.api;


/**
 * Where clause builder for any table
 * 
 * @author nhl
 *
 * @param <MODEL> model passed along and returned back
 * @param <RESULT> query result type
 */

public interface IClassicWhereClauseBuilderNamed<MODEL, RESULT>
		extends ISharedWhereClauseBuilderNamedAll<
			MODEL,
			RESULT,
			IClassicAndOrLogicalClausesNamed<MODEL, RESULT>,
			
			ISharedClauseComparableCommonAll_Compilable<MODEL, RESULT, ? extends Comparable<?>, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>,
			ISharedClauseComparableStringAll_Compilable<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>> {

}
