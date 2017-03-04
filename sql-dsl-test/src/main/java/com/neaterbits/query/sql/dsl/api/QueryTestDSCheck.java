package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface QueryTestDSCheck {

	void check(Consumer<DataConfig> testBuilder);

}
