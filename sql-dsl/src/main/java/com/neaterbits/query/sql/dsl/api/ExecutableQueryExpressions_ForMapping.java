package com.neaterbits.query.sql.dsl.api;

final class ExecutableQueryExpressionsForCompiledExpression
	
	extends ExecutableQueryForCompiledBase

	implements ExecutableQueryExpressions {
	
	private final CompiledExpression root;
	
	ExecutableQueryExpressionsForCompiledExpression(CompiledExpression root) {

		if (root == null) {
			throw new IllegalArgumentException("root == null");
		}

		this.root = root;
	}

	private CompiledExpression getExpressionAt(int level, int[] context) {
		if (level == 0 && context[0] != 0) {
			throw new IllegalStateException("Expexted to always be index 0 at inital level as only one root expression");
		}

		// Iterate down to level through expressions
		CompiledExpression cur = root;

		for (int i = 1; i < level; ++ i) {
			cur = cur.visit(expressionSubVisitor, context[i]);
		}
		
		return cur;
	}
	
	@Override
	public EExpressionType getExpressionType(int level, int[] context) {
		
		return getExpressionAt(level, context).visit(expressionTypeVisitor, null);
	}

	@Override
	public FunctionBase getFunction(int level, int[] context) {
		
		final CompiledFunctionExpression functionExpression = (CompiledFunctionExpression)getExpressionAt(level, context);
		
		return functionExpression.getFunction();
	}

	@Override
	public int getSubCount(int level, int[] context) {
		return getExpressionAt(level, context).visit(expressionSubCount, null);
	}

	@Override
	public Comparable<?> getValue(int level, int[] context) {
		final CompiledValueExpression valueExpression = (CompiledValueExpression)getExpressionAt(level, context);
		
		return valueExpression.getValue();
	}

	private static final CompiledExpressionVisitor<Integer, CompiledExpression> expressionSubVisitor = new CompiledExpressionVisitor<Integer, CompiledExpression>() {

		@Override
		public CompiledExpression onList(CompiledExpressionList list, Integer idx) {
			return list.getExpressions().get(idx);
		}

		@Override
		public CompiledExpression onField(CompiledFieldExpression field, Integer idx) {
			throw new UnsupportedOperationException("No subs");
		}

		@Override
		public CompiledExpression onValue(CompiledValueExpression value, Integer idx) {
			throw new UnsupportedOperationException("No subs");
		}

		@Override
		public CompiledExpression onFunction(CompiledFunctionExpression function, Integer idx) {
			// Sub within paramters
			return function.getParameters().get(idx);
		}

		@Override
		public CompiledExpression onNestedFunctionCalls(CompiledNestedFunctionCallsExpression nested, Integer idx) {
			throw new UnsupportedOperationException("TODO");
		}
	};

	private static final CompiledExpressionVisitor<Void, Integer> expressionSubCount = new CompiledExpressionVisitor<Void, Integer>() {

		@Override
		public Integer onList(CompiledExpressionList list, Void param) {
			return list.getExpressions().size();
		}

		@Override
		public Integer onField(CompiledFieldExpression field, Void param) {
			return 0;
		}

		@Override
		public Integer onValue(CompiledValueExpression value, Void param) {
			return 0;
		}

		@Override
		public Integer onFunction(CompiledFunctionExpression function, Void param) {
			return function.getParameters().size();
		}

		@Override
		public Integer onNestedFunctionCalls(CompiledNestedFunctionCallsExpression nested, Void param) {
			throw new UnsupportedOperationException();
		}
	};
	
	
	private static final CompiledExpressionVisitor<Void, EExpressionType> expressionTypeVisitor
			= new CompiledExpressionVisitor<Void, EExpressionType>() {

		@Override
		public EExpressionType onValue(CompiledValueExpression value, Void param) {
			return EExpressionType.VALUE;
		}
		
		@Override
		public EExpressionType onNestedFunctionCalls(CompiledNestedFunctionCallsExpression nested, Void param) {
			throw new UnsupportedOperationException("TODO");
		}
		
		@Override
		public EExpressionType onList(CompiledExpressionList list, Void param) {
			return EExpressionType.LIST;
		}

		@Override
		public EExpressionType onFunction(CompiledFunctionExpression function, Void param) {
			return EExpressionType.FUNCTION;
		}

		@Override
		public EExpressionType onField(CompiledFieldExpression field, Void param) {
			return EExpressionType.VALUE;
		}
	};
}
