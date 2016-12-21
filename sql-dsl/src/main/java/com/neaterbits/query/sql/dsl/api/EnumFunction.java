package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface EnumFunction<T, E extends Enum<E>> extends Function<T, E> {

}
