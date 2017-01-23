package com.neaterbits.query.sql.dsl.api;

final class CollectedClause_String<MODEL, RESULT, L extends ISharedLogicalClauses<MODEL, RESULT>>
		extends CollectedClause_Comparative<MODEL, RESULT, String, L>
		implements ISharedClauseComparableStringAll_Compilable<MODEL, RESULT, L> {

	CollectedClause_String(CollectedClauses<MODEL, RESULT> clause, Getter getter) {
		super(clause, getter);
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
		
		return addCondition(new CollectedCondition_StringStartsWith(getter, makeLiteralValue(value)));
	}

	@Override
	public L startsWith(Param<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new CollectedCondition_StringStartsWith(getter, makeParamValue(param)));
	}

	@Override
	public L endsWith(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_StringEndsWith(getter, makeLiteralValue(value)));
	}

	@Override
	public L endsWith(Param<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new CollectedCondition_StringEndsWith(getter, makeParamValue(param)));
	}

	@Override
	public L contains(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_StringContains(getter, makeLiteralValue(value)));
	}

	@Override
	public L contains(Param<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new CollectedCondition_StringContains(getter, makeParamValue(param)));
	}

	@Override
	public L matches(String regex) {
		if (regex == null) {
			throw new IllegalArgumentException("regex == null");
		}
		
		return addCondition(new CollectedCondition_StringMatches(getter, makeLiteralValue(regex)));
	}

	@Override
	public L matches(Param<String> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		return addCondition(new CollectedCondition_StringMatches(getter, makeParamValue(param)));
	}
}