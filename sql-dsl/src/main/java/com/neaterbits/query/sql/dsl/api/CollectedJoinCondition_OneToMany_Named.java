package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinCondition_OneToMany_Named extends CollectedJoinCondition_OneToMany {

	CollectedJoinCondition_OneToMany_Named(FunctionGetter collectionGetter, Collector_Joins subJoins) {
		super(collectionGetter, subJoins);
	}
}
