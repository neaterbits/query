package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNestedAndConsumerNamed<MODEL, RESULT, T extends ISharedAndClausesNamed<MODEL, RESULT>> extends Consumer<T> {

}
