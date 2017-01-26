package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

abstract class PreparedQueryCondition {

	abstract boolean isUnresolved();

	abstract void walk(Consumer<PreparedQueryConditionComparison> consumer);

}
