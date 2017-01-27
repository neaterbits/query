package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNestedAndConsumerNamed<MODEL, RESULT, T extends ISharedLogical_And_Named<MODEL, RESULT>> extends Consumer<T> {

}
