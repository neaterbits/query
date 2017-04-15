package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISupplierEnum<E extends Enum<E>> extends Supplier<E> {

}
