package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Metamodel;

final class CompileConditionParam {

	private final StringBuilder conditionSB;
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

		this.conditionSB = new StringBuilder();
		this.paramNameAssigner = paramNameAssigner;
		this.entityManager = entityManager;
		this.conditions = new ArrayList<>();
	}

	CompileConditionParam append(String s) {
		conditionSB.append(s);

		return this;
	}

	
	ConditionValueImpl getValue() {
		return value;
	}

	void setValue(ConditionValueImpl value) {
		this.value = value;
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

	EntityManager getEntityManager() {
		return entityManager;
	}
}
