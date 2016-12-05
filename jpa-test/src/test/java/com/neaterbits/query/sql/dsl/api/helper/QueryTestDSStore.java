package com.neaterbits.query.sql.dsl.api.helper;

import java.util.function.Consumer;

public interface QueryTestDSStore {

	QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder);

}
