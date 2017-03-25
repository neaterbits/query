package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

final class CollectedJoin_Alias extends CollectedJoin {

	CollectedJoin_Alias(EJoinType joinType, Supplier<?> from, Supplier<?> to, Collector_Joins subJoins) {
		super(
			joinType,
			new CollectedJoinCondition_Comparison_Alias(new SupplierGetter(from), new SupplierGetter(to)),
			subJoins);

		this.leftAlias = null;
		this.rightAlias = null;
	}

	CollectedJoin_Alias(EJoinType joinType, CollectionSupplier<?> collectionJoin, Collector_Joins subJoins, IAlias rhs) {
		super(
			joinType,
			new CollectedJoinCondition_OneToMany_Alias(new SupplierGetter(collectionJoin), rhs),
			subJoins);
		
		this.leftAlias = null;
		this.rightAlias = null;
	}
	
	// For classic
	
	private final IAlias leftAlias;
	private final IAlias rightAlias;
	
	CollectedJoin_Alias(EJoinType joinType, IAlias leftAlias, IAlias rightAlias, Collector_Joins subJoins) {
		super(joinType, leftAlias.getType(), rightAlias.getType(), subJoins);

		this.leftAlias = leftAlias;
		this.rightAlias = rightAlias;
	}

	IAlias getLeftAlias() {
		return leftAlias;
	}

	IAlias getRightAlias() {
		return rightAlias;
	}
}
