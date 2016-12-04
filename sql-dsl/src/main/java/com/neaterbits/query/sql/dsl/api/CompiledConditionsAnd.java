package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

final class CompiledConditionsAnd extends CompiledConditions {

	CompiledConditionsAnd(Collection<CompiledCondition> conditions) {
		super(conditions);
	}
}
