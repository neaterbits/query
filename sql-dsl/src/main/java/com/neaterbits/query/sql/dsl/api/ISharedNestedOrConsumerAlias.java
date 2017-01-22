package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNestedOrConsumerAlias<MODEL, RESULT, T extends ISharedOrClausesAlias<MODEL, RESULT>> extends Consumer<T> {

}

