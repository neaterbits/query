package com.neaterbits.query.sql.dsl.api;


final class Condition_ExpressionList_String_Named< 
		MODEL,
		RESULT,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>>
	
	extends Conditions_ExpressionList_Comparable_Base<
		MODEL,
		RESULT,
		String,
		
		RET, ISharedComparison_Comparable_String_All<MODEL, RESULT, RET>,
		
		RET,
		
		RET,
		ISharedLogical_Base<MODEL, RESULT>, // Alias
		
		//ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, RET>, // String
		
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
		ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
	
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
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>
	
	>
	
	implements ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, RET>
		
	
	
	{
	
	
	private Collector_Condition_String<MODEL, RESULT, ?> stringConditions;
	
	
	@Override
	ISharedComparison_Comparable_String_All<MODEL, RESULT, RET> assureComparable() {
		return assureNamedString();
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private final ISharedComparison_Comparable_String_All<MODEL, RESULT, RET> assureNamedString() {
		
		if (this.stringConditions != null) {
			throw new IllegalStateException("stringConditions already set");
		}

		this.stringConditions = new Collector_Condition_String<>(getRetClause(), collectExpressionListOrOne());
		
		return (ISharedComparison_Comparable_String_All)stringConditions;
	}
	

	
	// 	TODO go over constructor calls and use static utility methods? 
	Condition_ExpressionList_String_Named(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {
		super(clause, expression, EFieldAccessType.NAMED);
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
}