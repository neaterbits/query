package com.neaterbits.query.sql.dsl.api;


/**
 * Where clause builder for any table
 * 
 * @author nhl
 *
 * @param <MODEL> model passed along and returned back
 * @param <RESULT> query result type
 */

public interface ISQLLogical_Where_SingleResult_Named_Base<MODEL, RESULT>
		extends ISQLLogical_Where_Named_Base<
			MODEL,
			RESULT,
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>> {

}
