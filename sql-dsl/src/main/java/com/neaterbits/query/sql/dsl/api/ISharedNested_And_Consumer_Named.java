package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedNested_And_Consumer_Named<MODEL, RESULT, T extends ISharedLogical_And_Named<MODEL, RESULT>> extends Consumer<T> {

}
