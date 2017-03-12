package com.neaterbits.query.sql.dsl.api;

abstract class CollectedJoinCondition_OneToMany extends CollectedJoinCondition {

	private final Getter collectionGetter;

	CollectedJoinCondition_OneToMany(Getter collectionGetter, Collector_Joins subJoins) {
		super(subJoins);

		if (collectionGetter == null) {
			throw new IllegalArgumentException("collectionGetter == null");
		}
		
		this.collectionGetter = collectionGetter;
	}

	final Getter getCollectionGetter() {
		return collectionGetter;
	}
}
