package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.neaterbits.query.util.java8.Coll8;

abstract class CompiledConditions {

	private final List<CompiledCondition> conditions;
	private final boolean hasSubConditions;

	CompiledConditions(Collection<CompiledCondition> conditions) {

		if (conditions == null) {
			throw new IllegalArgumentException("conditions == null");
		}

		this.conditions = Collections.unmodifiableList(new ArrayList<>(conditions));
		this.hasSubConditions = Coll8.has(conditions, c -> c instanceof CompiledConditionNested);
	}

	final List<CompiledCondition> getConditions() {
		return conditions;
	}
	
	// Similiar to algorithm of AdhocConditionsNamed
	int getMaxDepth() {
		int ret;
		
		if (conditions != null && hasSubConditions) {
			
			ret = 1;
			
			for (CompiledCondition sub : conditions) {
				if (sub instanceof CompiledConditionNested) {
					final CompiledConditionNested nested = (CompiledConditionNested)sub;
					final int m = nested.getSub().getMaxDepth();
					
					if (m + 1 > ret) {
						ret = m + 1; // + 1 for current level
					}
				}
			}
		}
		else {
			ret = 1;
		}

		return ret;
		
	}
}

