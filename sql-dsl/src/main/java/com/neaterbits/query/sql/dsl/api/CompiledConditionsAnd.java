package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

final class CompiledConditionsAnd extends CompiledConditions {

	CompiledConditionsAnd(Collection<ConditionImpl> conditions) {
		super(conditions);
	}
}
