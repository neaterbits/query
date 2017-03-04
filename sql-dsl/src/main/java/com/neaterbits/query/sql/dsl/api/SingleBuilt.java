package com.neaterbits.query.sql.dsl.api;

/**
 * Result interface for a query that finds a single value
 * @author nhl
 *
 * @param <RESULT> type of result
 *  
 */

public interface SingleBuilt<RESULT> extends BuiltQueryOps<RESULT, RESULT, SinglePrepared<RESULT>> {

	
}
