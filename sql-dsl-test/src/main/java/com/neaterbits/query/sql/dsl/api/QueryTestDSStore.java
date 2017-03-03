package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface QueryTestDSStore {

	QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder);

}
