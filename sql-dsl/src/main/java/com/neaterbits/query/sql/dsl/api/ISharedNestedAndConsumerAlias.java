package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNestedAndConsumerAlias<MODEL, RESULT, T extends ISharedLogical_And_Alias<MODEL, RESULT>> extends Consumer<T> {

}
