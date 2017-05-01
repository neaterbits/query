package com.neaterbits.query.sql.dsl.api;

/*
import java.util.Arrays;
import java.util.List;
*/

abstract class CollectedCondition_NonNested extends CollectedCondition {

	private final Expression lhs;
	
	/*
	private final Getter getter;
	private CollectedFunctions functions;
	*/

	abstract EClauseOperator getOperator();

	CollectedCondition_NonNested(Expression lhs) {
		if (lhs == null) {
			throw new IllegalArgumentException("lhs == null");
		}

		this.lhs = lhs;
		//this.getter = null;
	}

	final Expression getLhs() {
		return lhs;
	}
	
	/*
	@Deprecated
	CollectedCondition_NonNested(Getter getter) {
		
		if (true) {
			throw new UnsupportedOperationException("Should pass expression");
		}
		

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.lhs = null;
		this.getter = getter;
	}

	@Deprecated
	final Getter getGetter() {
		
		// TODO: should change access API instead

		final Getter ret;
		
		if (lhs instanceof NestedFunctionCallsExpression) {
			ret = ((NestedFunctionCallsExpression)lhs).getField().getGetter();
		}
		else if (lhs instanceof FieldExpression) {
			ret = ((FieldExpression)lhs).getGetter();
		}
		else if (lhs instanceof FunctionExpression) {
			final List<Expression> params = ((FunctionExpression)lhs).getParameters();
			
			final FieldExpression field = (FieldExpression)params.get(0);
			
			ret = field.getGetter();
		}
		else {
			throw new UnsupportedOperationException("Unknown expression: " + lhs.getClass().getSimpleName());
		}
		
		return ret;
	}

	@Deprecated
	final CollectedFunctions getFunctions() {
		
		// TODO : change access API instead
		final CollectedFunctions ret;
		
		if (lhs instanceof NestedFunctionCallsExpression) {
			ret = ((NestedFunctionCallsExpression)lhs).getFunctions();
		}
		else if (lhs instanceof FieldExpression) {
			ret = null;
		}
		else if (lhs instanceof FunctionExpression) {
			ret = new CollectedFunctions(Arrays.asList((FunctionExpression)lhs));
		}
		else {
			throw new UnsupportedOperationException("Unknown expression: " + lhs.getClass().getSimpleName());
		}
		
		return ret;
	}

	@Deprecated
	final void setFunctions(CollectedFunctions functions) {
		if (true) {
			throw new UnsupportedOperationException("Should pass expression");
		}
		
		if (functions == null) {
			throw new IllegalArgumentException("functions == null");
		}

		if (this.functions != null) {
			throw new IllegalStateException("functions already set");
		}

		this.functions = functions;
	}
	*/
}
