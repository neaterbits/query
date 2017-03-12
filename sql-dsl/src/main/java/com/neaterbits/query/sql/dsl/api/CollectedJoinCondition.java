package com.neaterbits.query.sql.dsl.api;

abstract class CollectedJoinCondition extends CollectedItem {

	private final Collector_Joins subJoins;

	CollectedJoinCondition(Collector_Joins subJoins) {
		// may be null
		this.subJoins = subJoins;
	}

	final Collector_Joins getSubJoins() {
		return subJoins;
	}
}
