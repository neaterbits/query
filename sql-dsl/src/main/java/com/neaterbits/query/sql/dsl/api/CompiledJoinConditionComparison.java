package com.neaterbits.query.sql.dsl.api;

final class CompiledJoinConditionComparison extends CompiledJoinCondition {

	private final CompiledFieldReference left;
	private final CompiledFieldReference right;
	
	CompiledJoinConditionComparison(CollectedJoinConditionComparison original, CompiledFieldReference left, CompiledFieldReference right) {
		super(original, left.getSource(), right.getSource());
		
		this.left = left;
		this.right = right;
	}
	
	public CompiledFieldReference getLhs() {
		return left;
	}

	public CompiledFieldReference getRhs() {
		return right;
	}

	@Override
	EJoinConditionType getJoinConditionType() {
		return EJoinConditionType.COMPARISON;
	}
}
