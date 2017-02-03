package com.neaterbits.query.sql.dsl.api;


/**
 * Where clause builder for any table
 * 
 * @author nhl
 *
 * @param <MODEL> model passed along and returned back
 * @param <RESULT> query result type
 */

public interface IClassicLogical_Where_NonProcessResult_Named<MODEL, RESULT>
		extends IClassicLogical_Where_Named_Base<
			MODEL,
			RESULT,
			IClassicLogical_And_Or_Named<MODEL, RESULT>> {

}
