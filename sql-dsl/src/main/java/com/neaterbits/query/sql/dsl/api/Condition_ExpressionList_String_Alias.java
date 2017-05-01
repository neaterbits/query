package com.neaterbits.query.sql.dsl.api;

final class Condition_ExpressionList_String_Alias< 
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>>
		
	extends Conditions_ExpressionList_Base<
		MODEL,
		RESULT,
		R,
		
		RET,
		
		ISharedLogical_Base<MODEL, RESULT>,
		RET,
		
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, R, RET>,
		ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, RET>, // String

		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>
		
		
		
		>
		
		implements ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, RET>
		


{

	private final Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause;
	
	private Collector_Condition_String<MODEL, RESULT, ?> stringConditions;



	@SuppressWarnings({"unchecked", "rawtypes"})
	private final  ISharedComparison_Comparable_String_All<MODEL, RESULT, RET> assureNamedString() {

		if (this.stringConditions != null) {
			throw new IllegalStateException("stringConditions already set");
		}

		this.stringConditions = new Collector_Condition_String<>(clause, collectExpressionListOrOne());

		return (ISharedComparison_Comparable_String_All) stringConditions;
	}



	Condition_ExpressionList_String_Alias(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {
		super(expression, EFieldAccessType.NAMED);

		this.clause = clause;
	}
	
	@Override
	public RET startsWith(ValParam<String> s) {
		return assureNamedString().startsWith(s);
	}

	@Override
	public RET endsWith(ValParam<String> s) {
		return assureNamedString().endsWith(s);
	}

	@Override
	public RET contains(ValParam<String> s) {
		return assureNamedString().contains(s);
	}

	@Override
	public RET matches(ValParam<String> regex) {
		return assureNamedString().matches(regex);
	}

	@Override
	public RET isGreaterThan(String value) {
		return assureNamedString().isGreaterThan(value);
	}

	@Override
	public RET isGreaterOrEqualTo(String value) {
		return assureNamedString().isGreaterOrEqualTo(value);
	}

	@Override
	public RET isLessThan(String value) {
		return assureNamedString().isLessThan(value);
	}

	@Override
	public RET isLessOrEqualTo(String value) {
		return assureNamedString().isLessOrEqualTo(value);
	}

	@Override
	public RET isEqualTo(String other) {
		return assureNamedString().isEqualTo(other);
	}

	@Override
	public RET isNotEqualTo(String other) {
		return assureNamedString().isNotEqualTo(other);
	}

	@Override
	public RET in(String... values) {
		return assureNamedString().in(values);
	}

	@Override
	public RET isEqualTo(ValParam<String> other) {
		return assureNamedString().isEqualTo(other);
	}

	@Override
	public RET isNotEqualTo(ValParam<String> other) {
		return assureNamedString().isNotEqualTo(other);
	}

	@Override
	public RET in(InParam<String> param) {
		return assureNamedString().in(param);
	}

	@Override
	public RET startsWith(String s) {
		return assureNamedString().startsWith(s);
	}

	@Override
	public RET endsWith(String s) {
		return assureNamedString().endsWith(s);
	}

	@Override
	public RET contains(String s) {
		return assureNamedString().contains(s);
	}

	@Override
	public RET matches(String regex) {
		return assureNamedString().matches(regex);
	}

	@Override
	public RET isGreaterThan(ValParam<String> value) {
		return assureNamedString().isGreaterThan(value);
	}

	@Override
	public RET isGreaterOrEqualTo(ValParam<String> value) {
		return assureNamedString().isGreaterOrEqualTo(value);
	}

	@Override
	public RET isLessThan(ValParam<String> value) {
		return assureNamedString().isLessThan(value);
	}

	@Override
	public RET isLessOrEqualTo(ValParam<String> value) {
		return assureNamedString().isLessOrEqualTo(value);
	}
}