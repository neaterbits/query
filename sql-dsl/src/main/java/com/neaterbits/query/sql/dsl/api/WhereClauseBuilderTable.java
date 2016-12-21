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

public interface WhereClauseBuilderTable<MODEL, RESULT> extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<T, R> ISharedConditionClause<MODEL, RESULT, R, IClassicAndOrLogicalClausesTable<MODEL, RESULT>> where(Function<T, R> func);

    <T> ISharedStringClause<MODEL, RESULT, IClassicAndOrLogicalClausesTable<MODEL, RESULT>> where(StringFunction<T> func);

}
