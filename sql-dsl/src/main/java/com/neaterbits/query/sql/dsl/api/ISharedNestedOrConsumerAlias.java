package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNestedOrConsumerAlias<MODEL, RESULT, T extends ISharedLogical_Or_Alias<MODEL, RESULT>> extends Consumer<T> {

}

