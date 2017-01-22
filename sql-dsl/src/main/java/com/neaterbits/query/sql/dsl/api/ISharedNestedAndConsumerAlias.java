package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNestedAndConsumerAlias<MODEL, RESULT, T extends ISharedAndClausesAlias<MODEL, RESULT>> extends Consumer<T> {

}
