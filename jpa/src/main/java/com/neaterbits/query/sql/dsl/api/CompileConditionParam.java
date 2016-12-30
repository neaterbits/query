package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManager;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;
import com.neaterbits.query.util.java8.Coll8;

final class CompileConditionParam {

	private final PreparedQueryConditionsBuilder conditionsBuilder;
	private final ParamNameAssigner paramNameAssigner;
	private final EntityManager entityManager;
	private final List<JPACondition> conditions;

	private ConditionValueImpl value;

	CompileConditionParam(ParamNameAssigner paramNameAssigner, EntityManager entityManager) {

		if (paramNameAssigner == null) {
			throw new IllegalArgumentException("paramNameAssigner == null");
		}

		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager == null");
		}

		this.conditionsBuilder = new PreparedQueryConditionsBuilderJPA();
		this.paramNameAssigner = paramNameAssigner;
		this.entityManager = entityManager;
		this.conditions = new ArrayList<>();
	}

	boolean hasUnresolved() {
		return Coll8.has(conditions, condition -> condition instanceof JPAConditionUnresolved);
	}
	
	PreparedQueryConditionsBuilder getConditionsBuilder() {
		return conditionsBuilder;
	}

	ConditionValueImpl getValue() {
		return value;
	}

	void setValue(ConditionValueImpl value) {
		this.value = value;
	}

	/*
	void appendParam(Param<?> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		final String name = paramNameAssigner.getOrAllocateName(param);
		
		append(":").append(name);
	}
	
	private void clear() {
		conditionsBuilder.setLength(0);
	}
	
	ParamNameAssigner getParamNameAssigner() {
		return paramNameAssigner;
	}

	List<JPACondition> getConditions() {
		return conditions;
	}

	EntityManager getEntityManager() {
		return entityManager;
	}

	void addAllConditions(PreparedQueryBuilder sb, ParamValueResolver resolver) {
		addAllConditions(sb, conditions, resolver);
	}

	static void addAllConditions(PreparedQueryBuilder sb, List<JPACondition> conditions, ParamValueResolver resolver) {
		for (JPACondition condition : conditions) {
			condition.append(sb, resolver);
		}
	}
	*/
}
