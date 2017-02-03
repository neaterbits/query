package com.neaterbits.query.sql.dsl.api;


/**
 * Where clause builder for any table
 * 
 * @author nhl
 *
 * @param <MODEL> model passed along and returned back
 * @param <RESULT> query result type
 */

public interface IClassicLogical_Where_Named<MODEL, RESULT>
		extends ISharedLogical_Where_Named_All<
			MODEL,
			RESULT,
			IClassicLogical_And_Or_Named<MODEL, RESULT>,
			
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, IClassicLogical_And_Or_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, IClassicLogical_And_Or_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, IClassicLogical_And_Or_Named<MODEL, RESULT>>> {

}
