package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

abstract class CompiledConditions {

	private final List<ConditionImpl> conditions;

	CompiledConditions(Collection<ConditionImpl> conditions) {
		if (conditions == null) {
			throw new IllegalArgumentException("conditions == null");
		}

		this.conditions = Collections.unmodifiableList(new ArrayList<>(conditions));
	}

	List<ConditionImpl> getConditions() {
		return conditions;
	}
}
