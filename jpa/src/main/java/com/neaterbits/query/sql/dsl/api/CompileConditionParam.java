package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class CompileConditionParam {

	private final StringBuilder conditionSB;
	private final ParamNameAssigner paramNameAssigner;
	private final List<JPACondition> conditions;

	CompileConditionParam(ParamNameAssigner paramNameAssigner) {
		
		if (paramNameAssigner == null) {
			throw new IllegalArgumentException("paramNameAssigner == null");
		}

		this.conditionSB = new StringBuilder();
		this.paramNameAssigner = paramNameAssigner;
		this.conditions = new ArrayList<>();
	}

	CompileConditionParam append(String s) {
		conditionSB.append(s);

		return this;
	}

	void completeResolvedCondition() {
		final JPAConditionResolved resolved = new JPAConditionResolved(conditionSB.toString());

		conditions.add(resolved);
		
		clear();
	}

	<T extends JPAConditionUnresolved> void unresolvedCondition(Function<String, T> ctor) {

		final String prefix = conditionSB.toString();
		final JPAConditionUnresolved conditionUnresolved = ctor.apply(prefix);

		if (conditionUnresolved == null) {
			throw new IllegalArgumentException("conditionUnresolved == null");
		}

		conditions.add(conditionUnresolved);

		clear();
	}
	
	void appendParam(Param<?> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		final String name = paramNameAssigner.getName(param);
		
		append(":").append(name);
	}
	
	private void clear() {
		conditionSB.setLength(0);
	}
	
	ParamNameAssigner getParamNameAssigner() {
		return paramNameAssigner;
	}

	List<JPACondition> getConditions() {
		return conditions;
	}
	
}
