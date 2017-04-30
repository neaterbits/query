package com.neaterbits.query.sql.dsl.api;

final class Collector_Condition_String<MODEL, RESULT, L extends ISharedLogical_Base<MODEL, RESULT>>
		extends Collector_Condition_Comparative<MODEL, RESULT, String, L>
		implements ISharedComparison_Comparable_String_All_Compilable<MODEL, RESULT, L> {

	
	Collector_Condition_String(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {
		super(clause, expression);
	}

	private ConditionValue_Literal_String makeLiteralValue(String value) {
		
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return new ConditionValue_Literal_String(value);
	}
	
	@Override
	public L startsWith(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_StringStartsWith(lhs, makeLiteralValue(value)));
	}

	@Override
	public L startsWith(ValParam<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new CollectedCondition_StringStartsWith(lhs, makeParamValue(param)));
	}

	@Override
	public L endsWith(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_StringEndsWith(lhs, makeLiteralValue(value)));
	}

	@Override
	public L endsWith(ValParam<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new CollectedCondition_StringEndsWith(lhs, makeParamValue(param)));
	}

	@Override
	public L contains(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_StringContains(lhs, makeLiteralValue(value)));
	}

	@Override
	public L contains(ValParam<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new CollectedCondition_StringContains(lhs, makeParamValue(param)));
	}

	@Override
	public L matches(String regex) {
		if (regex == null) {
			throw new IllegalArgumentException("regex == null");
		}
		
		return addCondition(new CollectedCondition_StringMatches(lhs, makeLiteralValue(regex)));
	}

	@Override
	public L matches(ValParam<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new CollectedCondition_StringMatches(lhs, makeParamValue(param)));
	}
}
