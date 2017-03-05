package com.neaterbits.query.sql.dsl.api;


/**
 * A marker interface for whatever is after a function like sum() or avg() or sqrt()
 * @author nhl
 *
 * @param <MODEL>
 * @param <RESULT>
 * @param <RET>
 */
public interface ISharedFunction_Next<MODEL, RESULT, RET extends ISharedFunction_After<MODEL, RESULT>> {

}
