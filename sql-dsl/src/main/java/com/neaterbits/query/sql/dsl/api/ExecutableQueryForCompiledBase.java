package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

abstract class ExecutableQueryForCompiledBase {
	
	static boolean evaluateCondition(CompiledConditionComparison condition, Object instance, ConditionValuesScratch scratch) {
		
		throw new UnsupportedOperationException("TODO - evaluate expressions");
		/*
		// Must evaluate condition with params.
		// First figure lhs
		final Object lhs  = condition.getLhs().getValue(instance);

		// Then figure rhs
		final Object rhs = condition.getValue().visit(valueVisitor, scratch.getCollectedParams());
		
		// At last, evaluate
		scratch.initConditionScratchValues(lhs, rhs);
		
		final ScalarType scalarType = condition.getScalarType();
		
		final boolean ret;
		
		if (scalarType == ScalarType.STRING) {
			ret = condition.getOriginal().visit(stringEvaluator, scratch);
		}
		else {
			ret = condition.getOriginal().visit(comparableEvaluator, scratch);
		}

		return ret;
		*/
	}

	private static final ConditionEvaluator_Comparable comparableEvaluator = new ConditionEvaluator_Comparable();
	private static final ConditionEvaluator_Comparable_String stringEvaluator = new ConditionEvaluator_Comparable_String();
	
	
	private static final ConditionValueVisitor<ParamValueResolver, Object> valueVisitor = new ConditionValueVisitor<ParamValueResolver, Object>() {
		
		@Override
		public Object onParam(ConditionValue_Param value, ParamValueResolver paramValueResolver) {
			
			if (paramValueResolver == null) {
				throw new IllegalArgumentException("paramValueResolver == null");
			}
			
			return paramValueResolver.resolveParam(value.getParam());
		}
		
		@Override
		public Object onLiteralString(ConditionValue_Literal_String value, ParamValueResolver param) {
			return value.getLiteral();
		}
		
		@Override
		public Object onLiteralAny(ConditionValue_Literal_Any<?> value, ParamValueResolver param) {
			return value.getLiteral();
		}
		
		@Override
		public Object onGetter(ConditionValue_Getter value, ParamValueResolver param) {
			throw new UnsupportedOperationException("Should only call getters in joins");
		}
		
		@Override
		public Object onFieldReference(ConditionValue_FieldRerefence value, ParamValueResolver param) {
			throw new UnsupportedOperationException("Should only call getters in joins");
		}
		
		@Override
		public Object onArray(ConditionValue_Array value, ParamValueResolver param) {
			return value.getValues();
		}

		@Override
		public Object onList(ConditionValue_List value, ParamValueResolver param) {
			return value.getValues();
		}
	};

	
}
