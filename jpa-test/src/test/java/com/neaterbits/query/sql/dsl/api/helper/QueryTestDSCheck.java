package com.neaterbits.query.sql.dsl.api.helper;

import java.util.function.Consumer;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;

public interface QueryTestDSCheck {

	void check(Consumer<QueryDataSource> testBuilder);

}
