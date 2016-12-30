package com.neaterbits.query.sql.dsl.api;


import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;

abstract class PreparedQueryConditionsBuilder {

	abstract void addJoinCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right);

	abstract PreparedQueryConditionsBuilder addNested(ConditionsType type);

	abstract void addComparisonCondition(ConditionsType type, PreparedQueryConditionComparison condition);

	abstract void resolveFromParams(StringBuilder sb, ParamValueResolver resolver);
}
