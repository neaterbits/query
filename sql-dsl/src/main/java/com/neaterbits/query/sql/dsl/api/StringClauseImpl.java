package com.neaterbits.query.sql.dsl.api;

final class StringClauseImpl<MODEL, RESULT, L extends ISharedLogicalClauses<MODEL, RESULT>>
		extends ComparativeClauseImpl<MODEL, RESULT, String, L>
		implements ISharedClauseComparableStringAll_Compilable<MODEL, RESULT, L> {

	StringClauseImpl(ClausesImpl<MODEL, RESULT> clause, Getter getter) {
		super(clause, getter);
	}

	private ConditionValueLiteralStringImpl makeLiteralValue(String value) {
		
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
		
		return addCondition(new ConditionStringStartsWith(getter, makeLiteralValue(value)));
	}

	@Override
	public L startsWith(Param<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new ConditionStringStartsWith(getter, makeParamValue(param)));
	}

	@Override
	public L endsWith(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionStringEndsWith(getter, makeLiteralValue(value)));
	}

	@Override
	public L endsWith(Param<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new ConditionStringEndsWith(getter, makeParamValue(param)));
	}

	@Override
	public L contains(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionStringContains(getter, makeLiteralValue(value)));
	}

	@Override
	public L contains(Param<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new ConditionStringContains(getter, makeParamValue(param)));
	}

	@Override
	public L matches(String regex) {
		if (regex == null) {
			throw new IllegalArgumentException("regex == null");
		}
		
		return addCondition(new ConditionStringMatches(getter, makeLiteralValue(regex)));
	}

	@Override
	public L matches(Param<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new ConditionStringMatches(getter, makeParamValue(param)));
	}
}
