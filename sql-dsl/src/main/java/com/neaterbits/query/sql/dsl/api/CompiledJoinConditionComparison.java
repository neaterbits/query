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
	
	@SuppressWarnings("unchecked")
	@Override
	boolean evaluate(Object instance1, Object instance2) {
		final Object lhs = left.getValue(instance1);
		final Object rhs = right.getValue(instance2);

		final boolean ret;
		
		if (lhs == null) {
			ret = rhs == null;
		}
		else if (rhs == null) {
			ret = true;
		}
		else {
			ret = ((Comparable<Object>)lhs).compareTo((Comparable<Object>)rhs) == 0;
		}
		
		return ret;
	}
}
