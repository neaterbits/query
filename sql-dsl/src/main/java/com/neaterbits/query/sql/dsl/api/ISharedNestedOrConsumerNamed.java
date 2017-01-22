package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNestedOrConsumerNamed<MODEL, RESULT, T extends ISharedOrClausesNamed<MODEL, RESULT>> extends Consumer<T>{

}
