package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

/**
 * Where clause builder for any table
 * 
 * @author nhl
 *
 * @param <MODEL> model passed along and returned back
 * @param <RESULT> query result type
 */

public interface WhereClauseBuilderTable<MODEL, RESULT> extends WhereClauseBuilder<MODEL, RESULT> {

	<T, R> ConditionClause<MODEL, RESULT, R, AndOrLogicalClausesTable<MODEL, RESULT>> where(Function<T, R> func);
	
    <T> StringClause<MODEL, RESULT, AndOrLogicalClausesTable<MODEL, RESULT>> where(StringFunction<T> func);
	
}
