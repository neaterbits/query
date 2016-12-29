package com.neaterbits.query.sql.dsl.api;


final class CompiledJoinConditionOneToMany extends CompiledJoinCondition {

	private final CompiledFieldReference collection;
	
	CompiledJoinConditionOneToMany(
			CollectedJoinConditionOneToMany original,
			TypeMapSource left,
			TypeMapSource right,
			CompiledFieldReference collection) {
		
		super(original, left, right);
		
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		this.collection = collection;
	}

	CompiledGetter getCollectionGetter() {
		return collection.getGetter();
	}

	@Override
	EJoinConditionType getJoinConditionType() {
		return EJoinConditionType.ONE_TO_MANY;
	}
}
