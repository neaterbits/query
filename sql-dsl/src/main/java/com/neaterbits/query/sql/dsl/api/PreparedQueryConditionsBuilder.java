package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;

abstract class PreparedQueryConditionsBuilder {

	abstract void addCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right);
	
	abstract void addNested(ConditionsType type, List<PreparedQueryCondition> conditions);
	
	abstract void addConditions(ConditionsType type, List<PreparedQueryCondition> conditions);
	
	abstract void addAllConditions(StringBuilder sb, ParamValueResolver resolver);
}
