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
		extends ISharedLogical_Where_Named_All<
			MODEL,
			RESULT,
			IClassicAndOrLogicalClausesNamed<MODEL, RESULT>,
			
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, ? extends Comparable<?>, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>> {

}
