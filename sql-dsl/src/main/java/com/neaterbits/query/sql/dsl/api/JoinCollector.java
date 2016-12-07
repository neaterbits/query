package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

final class JoinCollector {

	private final List<CollectedJoin> joins;

	JoinCollector() {
		this.joins = new ArrayList<>();
	}
	
	void addJoin(CollectedJoin join) {
		if (join == null) {
			throw new IllegalArgumentException("join == null");
		}
		
		joins.add(join);
	}

	List<CollectedJoin> getJoins() {
		return joins;
	}
}
