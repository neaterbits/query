package com.neaterbits.query.sql.dsl.api;

/**
 * A referance to a parameter eg a "named parameter" in
 * and SQL query, useful when reusing a query - ie declaring the query as static
 * while reusing it
 * 
 * @author nhl
 *
 * @param <T> type of param, should correspond to what we are selecting against
 * 
 * Allows for preregistered queries.
 *
 */

public interface Param<T> {

}


