package com.neaterbits.query.sql.dsl.api;

abstract class CollectedJoinConditionOneToMany extends CollectedJoinCondition {

	private final Getter collectionGetter;

	CollectedJoinConditionOneToMany(Getter collectionGetter) {
		
		if (collectionGetter == null) {
			throw new IllegalArgumentException("collectionGetter == null");
		}
		
		this.collectionGetter = collectionGetter;
	}

	final Getter getCollectionGetter() {
		return collectionGetter;
	}
}
