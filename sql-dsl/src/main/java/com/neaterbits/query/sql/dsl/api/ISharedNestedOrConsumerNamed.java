package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNestedOrConsumerNamed<MODEL, RESULT, T extends ISharedLogical_Or_Named<MODEL, RESULT>> extends Consumer<T>{

}
