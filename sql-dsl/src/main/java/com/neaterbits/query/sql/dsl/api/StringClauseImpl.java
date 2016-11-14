package com.neaterbits.query.sql.dsl.api;

final class StringClauseImpl<MODEL, RESULT, L extends LogicalClauses<MODEL, RESULT>>
		extends ComparativeClauseImpl<MODEL, RESULT, String, L>
		implements StringClause<MODEL, RESULT, L> {

	StringClauseImpl(ClausesImpl<MODEL, RESULT> clause, StringFunction<?> getter) {
		super(clause, getter);
	}

	private ConditionValueLiteralStringImpl makeValue(String value) {
		
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return new ConditionValueLiteralStringImpl(value);
	}
	
	@Override
	public L startsWith(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionStringStartsWith(getter, makeValue(value)));
	}

	@Override
	public L endsWith(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionStringEndsWith(getter, makeValue(value)));
	}

	@Override
	public L contains(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionStringContains(getter, makeValue(value)));
	}

	@Override
	public L matches(String regex) {
		if (regex == null) {
			throw new IllegalArgumentException("regex == null");
		}
		
		return addCondition(new ConditionStringMatches(getter, makeValue(regex)));
	}
}
