package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

final class CompiledConditionsOr extends CompiledConditions {

	CompiledConditionsOr(Collection<CompiledCondition> conditions) {
		super(conditions);
	}
}
