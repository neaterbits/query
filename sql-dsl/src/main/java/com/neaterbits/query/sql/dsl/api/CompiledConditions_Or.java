package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

final class CompiledConditions_Or extends CompiledConditions {

	CompiledConditions_Or(Collection<CompiledCondition> conditions) {
		super(conditions);
	}
}
