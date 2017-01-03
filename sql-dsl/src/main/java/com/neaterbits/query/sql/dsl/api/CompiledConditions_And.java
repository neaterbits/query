package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

final class CompiledConditions_And extends CompiledConditions {

	CompiledConditions_And(Collection<CompiledCondition> conditions) {
		super(conditions);
	}
}
