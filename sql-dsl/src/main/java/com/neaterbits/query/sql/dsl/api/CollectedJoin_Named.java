package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class CollectedJoin_Named extends CollectedJoin {

	CollectedJoin_Named(EJoinType joinType, Class<?> leftType, Class<?> rightType) {
		super(joinType, leftType, rightType);
	}
	
	
	CollectedJoin_Named(EJoinType joinType, Function<?, ?> from, Function<?, ?> to, Collector_Joins subJoins) {
		super(
			joinType,
			new CollectedJoinCondition_Comparison_Named(
				new FunctionGetter(from),
				new FunctionGetter(to),
				subJoins)
			);
	}

	CollectedJoin_Named(EJoinType joinType, CollectionFunction<?, ?> collectionJoin, Collector_Joins subJoins) {
		super(
			joinType,
			new CollectedJoinCondition_OneToMany_Named(
				new FunctionGetter(collectionJoin),
				subJoins)
			);
	}
	
}
