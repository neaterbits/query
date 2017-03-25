package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinCondition_OneToMany_Alias extends CollectedJoinCondition_OneToMany {

	// TODO: perhaps default to a single alias if only one reference present, but for now must always pass alias
	
	private final IAlias rhs;
	
	CollectedJoinCondition_OneToMany_Alias(SupplierGetter collectionGetter, IAlias rhs) {
		super(collectionGetter);
		
		if (rhs == null) {
			throw new IllegalArgumentException("rhs == null");
		}
		
		this.rhs = rhs;
	}

	IAlias getRhs() {
		return rhs;
	}
}
